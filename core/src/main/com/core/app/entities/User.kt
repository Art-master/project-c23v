package com.core.app.entities

import app.domain.entities.IUser
import c23v.domain.entities.Message
import com.core.app.schemas.UserSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


@Table(UserSchema.TABLE_NAME)
class User : IUser, Message, UserDetails {

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

    @Column(UserSchema.LANG)
    override var lang: String = Locale.getDefault().toLanguageTag()

   // private val authorities: List<String> = emptyList()


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

/*    private fun getAuthorities(roles: Collection<Role>): Collection<GrantedAuthority>? {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority(role.getName()))
            role.getPrivileges().stream()
                .map { p -> SimpleGrantedAuthority(p.getName()) }
                .forEach(authorities::add)
        }
        return authorities
    }*/

    override fun getPassword() = ""
    override fun getUsername() = name
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true

}