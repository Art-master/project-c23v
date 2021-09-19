package com.network.app.repository

import com.network.app.entities.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {

    @Query("SELECT * FROM app.public.user WHERE id=$1 LIMIT 1")
    fun findOne(id: Long): Mono<User>

}
