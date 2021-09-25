package com.network.app.repository

import com.network.app.entities.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : R2dbcRepository<User, Long> {

    @Query("SELECT * FROM public.user WHERE id=$1 LIMIT 1")
    fun findOne(id: Long): Mono<User>


/*    @Query("INSERT INTO app.public.user VALUES (:user)")
    fun insertOne(user: User): User*/

}
