package app.domain.entities

/**
 * Basic interface for email
 */
interface IMailData {
    var id: Int
    var address: String
    var subject: String
    var text: String
}