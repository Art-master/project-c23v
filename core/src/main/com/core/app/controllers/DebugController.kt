package com.core.app.controllers

import com.core.app.entities.User
import com.core.app.services.DebugService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/debug")
class DebugController(val debugService: DebugService) {

    @GetMapping("/init_users/{count}")
    fun initTestUsers(@PathVariable count: Int): Flux<User> {
        return debugService.initTestUsers(count)
    }

}