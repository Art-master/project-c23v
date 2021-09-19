package com.network.app.controllers

import com.network.app.entities.User
import com.network.app.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class UserController(val userRepository: UserRepository) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Mono<User> {
        return userRepository.findOne(id)
    }

}