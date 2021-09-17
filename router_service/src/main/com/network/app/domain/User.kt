package com.network.app.domain

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@Table("user")
data class User(

    @Id
    @GeneratedValue
    var id: Long = 0
)