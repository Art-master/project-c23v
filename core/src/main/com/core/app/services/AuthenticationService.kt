package com.core.app.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class AuthenticationService(val messageSource: ResourceBundleMessageSource) {

    @Value("\${TELECOM_SERVICE_ADDR}")
    private lateinit var telecomServiceAddr: String

    private val webClient = WebClient.create()

    fun sendSms() {

    }

    fun confirmPhoneNumber(phoneNumber: String) {
        webClient.get()
            .uri("$telecomServiceAddr/confirm_phone_number")
            .attribute("phone_number", phoneNumber)
            .retrieve()
    }

    fun isPhoneNumberConfirmed(phoneNumber: String): Mono<Boolean> {
        return webClient.get()
            .uri("$telecomServiceAddr/is_phone_number_confirmed")
            .attribute("phone_number", phoneNumber)
            .retrieve()
            .bodyToMono(Boolean::class.java)
    }

}