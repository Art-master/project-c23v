package com.network.app.entities

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import app.domain.User as DomainUser

@Entity
@Table("user")
class User : DomainUser() {
    @Id
    @GeneratedValue
    override var id: Long = 0
}