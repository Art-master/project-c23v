package com.core.app.controllers

import com.core.app.services.AuthenticationService
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthenticationController(
    val messageSource: ResourceBundleMessageSource,
    val authenticationService: AuthenticationService
) {

    @GetMapping("/send_sms/{phone_number}")
    fun sendSms(@PathVariable("phone_number") phoneNumber: String) {
        authenticationService.sendSms()
    }

    @GetMapping("/waiting_for_call/{phone_number}")
    fun waitingForCall(@PathVariable("phone_number") phoneNumber: String) {
        authenticationService.waitingForCall()
    }
}