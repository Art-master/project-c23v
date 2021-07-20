package com.network.c23v.controllers

import com.network.c23v.domain.User
import com.network.c23v.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class UserController {

/*    @Autowired
    lateinit var webClient: WebClient
    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Mono<User> {
        return userRepository.findById(id)
    }*/

}