package com.core.app.services

import com.core.app.entities.User
import com.core.app.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.stream.Collectors

@Service
class DebugService(val userRepository: UserRepository) {


    fun initTestUsers(usersCount: Int): Flux<User> {

        val users = MutableList(usersCount) {
            User().apply {
                name = "user $it"
                phoneNumber = "791690571$it"
            }
        }

        val usersData = userRepository.saveAll(users)

        with(userRepository) {
            usersData
                .map { it.id }
                .collect(Collectors.toList())
                .subscribe {
                    val mainUser = User().apply {
                        name = "Admin"
                        friendsIds = it
                    }
                    save(mainUser)
                }
                .dispose()
        }

        return usersData
    }
}