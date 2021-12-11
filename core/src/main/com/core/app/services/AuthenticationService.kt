package com.core.app.services

import c23v.domain.entities.schemas.PhoneConfirmationData
import com.core.app.messages.MessagesConsumer
import com.core.app.messages.MessagesProducer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.annotation.PostConstruct

@Service
class AuthenticationService(val messageSource: ResourceBundleMessageSource) {

    @Value("\${TELECOM_SERVICE_ADDR}")
    private lateinit var telecomServiceAddr: String

    @Value("\${BOOTSTRAP_SERVERS}")
    private lateinit var bootstrapServers: String

    private val webClient = WebClient.create()

    private val logger = LoggerFactory.getLogger(AuthenticationService::class.simpleName)

    private lateinit var producer: MessagesProducer
    private lateinit var consumer: MessagesConsumer

    fun sendSms() {

    }

    @PostConstruct
    fun init() {
        producer = MessagesProducer(bootstrapServers)
        consumer = MessagesConsumer(bootstrapServers)
    }

    fun confirmPhoneNumber(phoneNumber: String): Mono<String> {
        return producer
            .sendMessage("test", PhoneConfirmationData(callPhone = phoneNumber))
            .flatMap {
                consumer.consumeMessages("test2")
            }
            .toMono()
            .flatMap {
                val data = it.value() as PhoneConfirmationData
                logger.debug("message received $data")
                Mono.just(data.callPhone)
            }
    }

    fun confirmPhoneNumberRest(phoneNumber: String): Mono<String> {
        return webClient.post()
            .uri { builder ->
                builder.path("$telecomServiceAddr/telecom/confirm_phone_number")
                    .queryParam("phone_number", phoneNumber)
                    .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)
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