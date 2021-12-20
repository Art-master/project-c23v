package com.core.app.services

import c23v.domain.entities.Message
import com.core.app.entities.User
import com.core.app.errors.ProjectException
import com.core.app.repository.UserRepository
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserService(
    val userRepository: UserRepository
) {

    fun create(user: User): Mono<User> {
        return userRepository.save(user)
    }

}