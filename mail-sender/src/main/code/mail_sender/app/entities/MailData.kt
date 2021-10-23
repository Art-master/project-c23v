package code.mail_sender.app.entities

import app.domain.entities.IMailData
import code.mail_sender.app.schemas.Schema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(Schema.TABLE_NAME)
data class MailData(

    @Id
    @Column(Schema.ID)
    override var id: Int,

    @Column(Schema.TEXT)
    override var text: String,

    @Column(Schema.ADDRESS)
    override var address: String,

    @Column(Schema.SUBJECT)
    override var subject: String,
) : IMailData