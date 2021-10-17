package com.gateway.app.services

import com.gateway.app.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

}