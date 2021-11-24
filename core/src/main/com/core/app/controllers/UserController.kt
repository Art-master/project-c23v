package com.core.app.controllers

import app.domain.entities.IResponse
import com.core.app.entities.User
import com.core.app.extensions.getMessage
import com.core.app.services.UserService
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.lang.Exception
import java.lang.RuntimeException


@RestController
@RequestMapping("/user")
class UserController(val messageSource : ResourceBundleMessageSource, val userService: UserService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long, user: User): Mono<User> {
        val message = messageSource.getMessage("test", "ru_RU")
        return userService.userRepository.findOne(id)
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun register(@RequestBody userCredentials: User): Mono<out IResponse> {
        return userService.register(userCredentials)
    }

    @GetMapping("/test")
    fun test(): Mono<Exception> {
        return Mono.error(RuntimeException())
        //return userService.register(userCredentials)
    }
}