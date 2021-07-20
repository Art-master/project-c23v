package com.network.c23v.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class User {

    @Id
    var id: Long = 0
}