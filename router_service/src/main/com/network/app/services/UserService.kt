package com.network.app.services

import com.network.app.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

}