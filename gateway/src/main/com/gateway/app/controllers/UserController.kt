package com.gateway.app.controllers

import com.gateway.app.entities.User
import com.gateway.app.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Mono<User> {
        return userService.userRepository.findOne(id)
    }
}