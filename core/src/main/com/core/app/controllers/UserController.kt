package com.core.app.controllers

import com.core.app.entities.User
import com.core.app.extensions.getMessage
import com.core.app.services.UserService
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/user")
class UserController(val messageSource : ResourceBundleMessageSource, val userService: UserService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long, user: User): Mono<User> {
        val message = messageSource.getMessage("test", "ru_RU")
        return userService.userRepository.findOne(id)
    }
}