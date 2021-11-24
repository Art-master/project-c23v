package com.core.app.config

import actuator.FeaturesEndpoint
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfig {

    @Value("\${server.custom.security:true}")
    private val isSecurityEnabled = true

    @Throws(Exception::class)
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return if (isSecurityEnabled) {
            http
                .authorizeExchange()
                .pathMatchers("/admin")
                .hasAuthority("ROLE_ADMIN")
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .matchers(EndpointRequest.to(FeaturesEndpoint::class.java)).permitAll()
                .anyExchange().authenticated()
                .and()
                //.formLogin()
                //.and()
                .csrf().disable()
                .build()

        } else http.authorizeExchange().anyExchange().permitAll().and().build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}