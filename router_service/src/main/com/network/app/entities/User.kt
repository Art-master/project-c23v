package com.network.app.entities

import app.domain.entities.BaseUser
import com.network.app.schemas.UserSchema
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table(UserSchema.TABLE_NAME)
class User : BaseUser() {

    @Id
    @GeneratedValue
    @Column(name = UserSchema.ID)
    override var id: Long = 0

    @Column(name = UserSchema.NAME)
    override var name: String = ""

    @Column(name = UserSchema.LAST_NAME)
    override var lastName: String = ""

    @Column(unique = true, name = UserSchema.PHONE_NUMBER)
    override var phoneNumber: Long = 0

    @Column(name = UserSchema.FRIENDS_IDS)
    @ElementCollection
    override var friendsIds: List<Long> = emptyList()

    @Column(name = UserSchema.AVATAR_ID)
    override var avatarId: Long = 0

    @Column(name = UserSchema.CONVERSATIONS_IDS)
    @ElementCollection
    override var conversationsIds: List<Long> = emptyList()
}