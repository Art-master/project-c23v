package com.core.app.entities

import app.domain.entities.IUser
import com.core.app.schemas.UserSchema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(UserSchema.TABLE_NAME)
class User(

    @Id
    @Column(UserSchema.ID)
    override var id: Long = 0,

    @Column(UserSchema.NAME)
    override var name: String = "",

    @Column(UserSchema.LAST_NAME)
    override var lastName: String = "",

    @Column(UserSchema.PHONE_NUMBER)
    override var phoneNumber: Long = 0,

    @Column(UserSchema.FRIENDS_IDS)
    override var friendsIds: List<Long> = emptyList(),

    @Column(UserSchema.AVATAR_ID)
    override var avatarId: Long = 0,

    @Column(UserSchema.CONVERSATIONS_IDS)
    override var conversationsIds: List<Long> = emptyList()
) : IUser {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (lastName != other.lastName) return false
        if (phoneNumber != other.phoneNumber) return false
        if (friendsIds != other.friendsIds) return false
        if (avatarId != other.avatarId) return false
        if (conversationsIds != other.conversationsIds) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + friendsIds.hashCode()
        result = 31 * result + avatarId.hashCode()
        result = 31 * result + conversationsIds.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', lastName='$lastName', phoneNumber=$phoneNumber, friendsIds=$friendsIds, avatarId=$avatarId, conversationsIds=$conversationsIds)"
    }
}