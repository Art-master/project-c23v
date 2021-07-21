package authresolver

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
class AuthResolverController {
    @GetMapping("/customer/welcome")
    fun sayWelcomeToCustomer(principal: Mono<Principal>): Mono<String> {
        return principal
            .map { obj: Principal -> obj.name }
            .map { name: String? -> String.format("Welcome to our site, %s!", name) }
    }

    @GetMapping("/employee/welcome")
    fun sayWelcomeToEmployee(principal: Mono<Principal>): Mono<String> {
        return principal
            .map { obj: Principal -> obj.name }
            .map { name: String? -> String.format("Welcome to our company, %s!", name) }
    }
}