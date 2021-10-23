package code.sms_sender.app.entities

import app.domain.entities.ISmsData
import code.sms_sender.app.schemas.Schema
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(Schema.TABLE_NAME)
data class SmsData(

    @Id
    @Column(Schema.ID)
    override var id: Int,

    @Column(Schema.TEXT)
    override var text: String,

    @Column(Schema.PHONE_NUMBER)
    override var phoneNumber: Int,

    ) : ISmsData