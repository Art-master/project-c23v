package app.domain.entities

/**
 * Basic abstract class for users Dialog
 * NOTE: Each field must be abstract type of
 */
abstract class BaseConversation {
    abstract var id: Long
    abstract var name: String
    abstract var type: ConversationType
}