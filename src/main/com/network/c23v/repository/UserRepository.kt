package com.network.c23v.repository

import com.network.c23v.domain.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {

/*    @Query("SELECT * FROM c23v.public.user WHERE id=$1 LIMIT 1")
    fun findOne(id: Long): Mono<User>*/

}
