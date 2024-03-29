package app.domain.entities

import java.util.*

/**
 * Basic interface for application User
 */
interface IUser {
    var id: Long
    var name: String
    var lastName: String
    var phoneNumber: String
    var friendsIds: List<Long>
    var avatarId: Long
    var conversationsIds: List<Long>
    var lang: String
}