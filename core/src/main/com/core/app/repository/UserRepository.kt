package com.core.app.repository

import com.core.app.entities.User
import com.core.app.schemas.UserSchema.ID
import com.core.app.schemas.UserSchema.PHONE_NUMBER
import com.core.app.schemas.UserSchema.TABLE_NAME
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : R2dbcRepository<User, Long> {

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID=$1 LIMIT 1")
    fun findOne(id: Long): Mono<User>

    @Query("SELECT * FROM $TABLE_NAME WHERE $PHONE_NUMBER=$1 LIMIT 1")
    fun findOneByPhoneNumber(phoneNumber: String): Mono<User>
}
