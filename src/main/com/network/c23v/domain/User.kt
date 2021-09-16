package com.network.c23v.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(

    @Id
    @GeneratedValue
    var id: Long = 0
)