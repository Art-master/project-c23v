package code.mail_sender.app.services

import app.domain.entities.ISmsData
import code.mail_sender.app.entities.MailData
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class MailService(val emailSender: JavaMailSender) {

    fun sendMessage(data: MailData) {
        val msg = SimpleMailMessage().apply {
            setFrom("art.master.app@gmail.com")
            setTo("ggn.master@gmail.com")
            setSubject("Java 20 new hot features")
            setText("Java 20 new hot features. No attachments :(")
        }

        emailSender.send(msg)
    }
}