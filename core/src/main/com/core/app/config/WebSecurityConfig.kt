package com.core.app.config

import actuator.FeaturesEndpoint
import com.core.app.entities.User
import com.core.app.services.AuthenticationService
import com.core.app.services.CustomUserDetailsService
import com.core.app.services.UserService
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.lang.RuntimeException


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfig(
    private val userService: UserService,
    private val userDetailsService: CustomUserDetailsService,
    private val authenticationService: AuthenticationService
) {

    private val isSecurityEnabled = true //DANGER

    @Throws(Exception::class)
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return if (isSecurityEnabled) {
            http
                .authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/debug/**").permitAll()
                .matchers(EndpointRequest.to(FeaturesEndpoint::class.java)).permitAll()

                .anyExchange().authenticated()
                .and()
                .formLogin().disable().httpBasic().and()
                .csrf().disable()
                .build()

        } else http.authorizeExchange()
            .anyExchange()
            .permitAll().and()
            .csrf().disable()
            .build()
    }

    @Bean
    protected fun reactiveAuthenticationManager(): ReactiveAuthenticationManager? {
        return ReactiveAuthenticationManager { authentication: Authentication ->
            try {
                val login = authentication.principal as String
                if (login.isEmpty()) throw RuntimeException("login must be not empty")

                authenticationService.isPhoneNumberConfirmed(login)
                    .flatMap {
                        if (it == "401") { // number confirmed
                            checkUser(authentication)
                        } else Mono.just(authentication)
                    }
            } catch (e: UsernameNotFoundException) {
                Mono.error(e)
            }
        }
    }

    private fun checkUser(authentication: Authentication): Mono<Authentication> {
        return userDetailsService.findByUsername(authentication.principal as String)
            .switchIfEmpty {
                val user = User().apply {
                    phoneNumber = authentication.principal.toString()
                }
                userService.create(user).cast(UserDetails::class.java)
            }
            .cast(User::class.java)
            .flatMap {
                Mono.just(UsernamePasswordAuthenticationToken(it.phoneNumber, "", it.authorities))
            }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}