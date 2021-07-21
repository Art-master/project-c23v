package com.network.c23v.config

import com.network.c23v.actuator.FeaturesEndpoint
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfig {
    @Throws(Exception::class)
    @Bean
    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return http.authorizeExchange()
            .pathMatchers("/admin")
            .hasAuthority("ROLE_ADMIN")
            .matchers(EndpointRequest.to(FeaturesEndpoint::class.java))
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .formLogin()
            .and()
            .csrf()
            .disable()
            .build()
    }

    @Bean
    open fun userDetailsService(): MapReactiveUserDetailsService? {
        val user = User
            .withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build()
        val admin = User
            .withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build()
        return MapReactiveUserDetailsService(user, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}