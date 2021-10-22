package app.domain.entities

/**
 * Basic interface for users Dialog
 */
interface IConversation {
    var id: Long
    var name: String
    var type: ConversationType
}