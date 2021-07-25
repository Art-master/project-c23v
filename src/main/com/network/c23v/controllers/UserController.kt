package com.network.c23v.controllers

import com.network.c23v.domain.User
import com.network.c23v.repository.UserRepository
import org.springframework.http.ResponseEntity
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
        val r213 = 1
        return userRepository.findOne(id)
        //return Mono.just(User())
    }

}