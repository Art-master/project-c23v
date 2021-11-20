package com.core.app.entities

import app.domain.entities.IUser
import app.domain.entities.IResponse
import com.core.app.schemas.UserSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Table(UserSchema.TABLE_NAME)
class User : IUser, IResponse, UserDetails {

    @Id
    @Column(UserSchema.ID)
    override var id: Long = 0

    @Column(UserSchema.NAME)
    override var name: String = ""

    @Column(UserSchema.LAST_NAME)
    override var lastName: String = ""

    @Column(UserSchema.PHONE_NUMBER)
    override var phoneNumber: String = ""

    @Column(UserSchema.FRIENDS_IDS)
    override var friendsIds: List<Long> = emptyList()

    @Column(UserSchema.AVATAR_ID)
    override var avatarId: Long = 0

    @Column(UserSchema.CONVERSATIONS_IDS)
    override var conversationsIds: List<Long> = emptyList()

    @Column(UserSchema.LOCALE)
    override var locale: Locale = Locale.getDefault()


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword() = ""
    override fun getUsername() = name
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

}