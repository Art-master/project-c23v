package app.domain.entities

/**
 * Basic abstract class for application User
 * NOTE: Each field must be abstract type of
 */
abstract class BaseUser {
    abstract var id: Long
    abstract var name: String
    abstract var lastName: String
    abstract var phoneNumber: Long
    abstract var friendsIds: List<Long>
    abstract var avatarId: Long
    abstract var conversationsIds: List<Long>
}