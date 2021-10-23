package code.mail_sender.app.controllers

import app.domain.entities.ISmsData
import code.mail_sender.app.services.MailService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class MailController(val mailService: MailService) {

    @GetMapping("/send")
    fun sendMessage(@RequestBody data: ISmsData) {
        return mailService.sendMessage(data)
    }
}