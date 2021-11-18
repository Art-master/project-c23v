package com.core.app.services

import com.core.app.repository.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails> {
        return userRepository
            .findOneByPhoneNumber(username!!)
            .cast(UserDetails::class.java)
    }
}