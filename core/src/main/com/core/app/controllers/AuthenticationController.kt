package com.core.app.controllers

import com.core.app.services.AuthenticationService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/auth")
class AuthenticationController(val authenticationService: AuthenticationService) {

    @GetMapping("/send_sms/{phone_number}")
    fun sendSms(@PathVariable("phone_number") phoneNumber: String) {
        authenticationService.sendSms()
    }

    @PostMapping("confirm_phone_number")
    fun confirmPhoneNumber(
        @RequestParam(required = false, defaultValue = "ru_RU") lang: String = "ru_RU",
        @RequestParam("phone_number") phone: String
    ): Mono<String> {
        return authenticationService.confirmPhoneNumber(phone)
    }

    @GetMapping("is_phone_number_confirmed")
    fun isPhoneNumberConfirmed(
        @RequestParam(required = false, defaultValue = "ru_RU") lang: String,
        @RequestParam("phone_number") phone: String
    ): Mono<String> {
        return authenticationService.isPhoneNumberConfirmed(phone)
    }
}