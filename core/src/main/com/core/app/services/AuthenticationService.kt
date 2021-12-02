package com.core.app.services

import com.core.app.messages.MessagesConsumer
import com.core.app.messages.MessagesProducer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kafka.receiver.ReceiverRecord
import reactor.kotlin.core.publisher.toMono

@Service
class AuthenticationService(val messageSource: ResourceBundleMessageSource) {

    @Value("\${TELECOM_SERVICE_ADDR}")
    private lateinit var telecomServiceAddr: String

    @Value("\${BOOTSTRAP_SERVERS}")
    private lateinit var bootstrapServers: String

    private val webClient = WebClient.create()

    fun sendSms() {

    }

    fun confirmPhoneNumber(phoneNumber: String): Mono<String> {
        return MessagesProducer(bootstrapServers)
            .sendMessage("test", phoneNumber)
            .delayUntil {
                MessagesConsumer(bootstrapServers).consumeMessages("test")
            }
            .toMono()
            .flatMap {
                Mono.just("")
            }

/*        return webClient.post()
            .uri { builder ->
                builder.path("$telecomServiceAddr/telecom/confirm_phone_number")
                    .queryParam("phone_number", phoneNumber)
                    .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)*/
    }

    fun isPhoneNumberConfirmed(phoneNumber: String): Mono<String> {
        return webClient.get()
            .uri { builder ->
                builder.path("$telecomServiceAddr/telecom/is_phone_number_confirmed")
                    .queryParam("phone_number", phoneNumber)
                    .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)
    }

}