package authresolver

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class CustomWebSecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange()
            .pathMatchers("/**")
            .authenticated()
            .and()
            .httpBasic()
            .disable()
            .addFilterAfter(authenticationWebFilter(), SecurityWebFiltersOrder.REACTOR_CONTEXT)
            .build()
    }

    fun authenticationWebFilter(): AuthenticationWebFilter {
        return AuthenticationWebFilter(resolver())
    }

    fun resolver(): ReactiveAuthenticationManagerResolver<ServerWebExchange> {
        return ReactiveAuthenticationManagerResolver { exchange: ServerWebExchange ->
            if (exchange
                    .request
                    .path
                    .subPath(0)
                    .value()
                    .startsWith("/employee")
            ) {
                return@ReactiveAuthenticationManagerResolver Mono.just(employeesAuthenticationManager())
            }
            Mono.just(customersAuthenticationManager())
        }
    }

    fun customersAuthenticationManager(): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManager { authentication: Authentication ->
            customer(authentication)
                .switchIfEmpty(
                    Mono.error(
                        UsernameNotFoundException(
                            authentication
                                .principal
                                .toString()
                        )
                    )
                )
                .map {
                    UsernamePasswordAuthenticationToken(
                        authentication.principal,
                        authentication.credentials, listOf(SimpleGrantedAuthority("ROLE_USER"))
                    )
                }
        }
    }

    fun employeesAuthenticationManager(): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManager { authentication: Authentication ->
            employee(authentication)
                .switchIfEmpty(
                    Mono.error(
                        UsernameNotFoundException(
                            authentication
                                .principal
                                .toString()
                        )
                    )
                )
                .map {
                    UsernamePasswordAuthenticationToken(
                        authentication.principal,
                        authentication.credentials, listOf(SimpleGrantedAuthority("ROLE_USER"))
                    )
                }
        }
    }

    fun customer(authentication: Authentication): Mono<String> {
        return Mono.justOrEmpty(
            if (authentication
                    .principal
                    .toString()
                    .startsWith("customer")
            ) authentication
                .principal
                .toString() else null
        )
    }

    fun employee(authentication: Authentication): Mono<String> {
        return Mono.justOrEmpty(
            if (authentication
                    .principal
                    .toString()
                    .startsWith("employee")
            ) authentication
                .principal
                .toString() else null
        )
    }
}