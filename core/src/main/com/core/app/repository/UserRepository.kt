package com.core.app.repository

import com.core.app.entities.User
import com.core.app.schemas.UserSchema
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : R2dbcRepository<User, Long> {

    @Query("SELECT * FROM ${UserSchema.TABLE_NAME} WHERE id=$1 LIMIT 1")
    fun findOne(id: Long): Mono<User>
}
