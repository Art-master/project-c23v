package services

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GreetService {
    @PreAuthorize("hasRole('ADMIN')")
    fun greet(): Mono<String> {
        return Mono.just("Hello from service!")
    }
}