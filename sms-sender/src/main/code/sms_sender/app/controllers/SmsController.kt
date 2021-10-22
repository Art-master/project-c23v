package code.sms_sender.app.controllers

import app.domain.entities.ISmsData
import code.sms_sender.app.services.SmsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(val smsService: SmsService) {

    @GetMapping("/send")
    fun sendMessage(@RequestBody data: ISmsData) {
        return smsService.sendMessage(data)
    }
}