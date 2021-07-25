package com.network.c23v.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("user")
class User {

    @Id
    @Column("id")
    var id: Long = 0
}