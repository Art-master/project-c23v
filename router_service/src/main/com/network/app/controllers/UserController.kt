package com.network.app.controllers

import com.network.app.entities.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/user")
class UserController() {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Mono<User> {
        return Mono.just(User())
    }

    @GetMapping("/init/{count}")
    fun initTestUsers(@PathVariable count: Int) {
        //userService.initTestUsers(count)
    }

}