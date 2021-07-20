package com.network.c23v.repository

import com.network.c23v.domain.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : ReactiveCrudRepository<User, Long>