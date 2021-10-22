package app.domain.entities

/**
 * Basic interface for application User
 */
interface IUser {
    var id: Long
    var name: String
    var lastName: String
    var phoneNumber: Long
    var friendsIds: List<Long>
    var avatarId: Long
    var conversationsIds: List<Long>
}