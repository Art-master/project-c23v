package com.core.app.services

import com.core.app.entities.User
import com.core.app.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toMono

@Service
class DebugService(val userRepository: UserRepository) {


    fun initTestUsers(usersCount: Int): Flux<User> {

        val users = MutableList(usersCount) {
            User().apply {
                name = "user $it"
                phoneNumber = "791690571$it"
            }
        }

        return userRepository
            .saveAll(users)
            .collectList()
            .toMono()
            .doOnNext { user ->
                val mainUser = User().apply {
                    name = "Admin"
                    phoneNumber = "+79263445673"
                    friendsIds = user.map { it.id }
                }
                userRepository.save(mainUser).subscribe()
            }
            .flatMapMany {
                Flux.fromStream(it.stream())
            }
    }
}