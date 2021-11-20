package com.core.app.services

import app.domain.entities.IResponse
import com.core.app.entities.User
import com.core.app.errors.ProjectException
import com.core.app.repository.UserRepository
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserService(
    val userRepository: UserRepository,
    val source: ResourceBundleMessageSource,
    val authenticationManager: AbstractUserDetailsReactiveAuthenticationManager
) {

    fun register(user: User): Mono<out IResponse> {
        if (userRepository.existsByPhoneNumber(user.phoneNumber)) {
            val message = source.getMessage("", arrayOf(), user.locale)
            return Mono.error(ProjectException(message))
        }

        return userRepository.save(user)
    }

    fun authUserManually(user: User, pass: String) {
        val authReq = UsernamePasswordAuthenticationToken(user, pass)
        authenticationManager.authenticate(authReq).subscribe {
            val sc: SecurityContext = SecurityContextHolder.getContext()
            sc.authentication = it
        }
    }

}