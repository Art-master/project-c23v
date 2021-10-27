package code.mail_sender.app.controllers

import code.mail_sender.app.entities.MailData
import code.mail_sender.app.services.MailService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mail")
class MailController(val mailService: MailService) {

    @GetMapping("/send")
    fun sendMessage() {
        return mailService.sendMessage(MailData(1, "", "", ""))
    }
}