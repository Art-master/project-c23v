package com.core.app.services

import c23v.domain.entities.Error
import c23v.domain.entities.schemas.PhoneConfirmationData
import c23v.domain.entities.topics.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.DependsOn
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import kotlin.random.Random

@DependsOn("producer", "consumer")
@Service
class AuthenticationService(
    private val messageSource: ResourceBundleMessageSource,
    private var producer: MessagesProducerService,
    private val consumer: MessagesConsumerService
) {

    @Value("\${TELECOM_SERVICE_ADDR}")
    private lateinit var telecomServiceAddr: String

    private val webClient = WebClient.create()

    private val logger = LoggerFactory.getLogger(AuthenticationService::class.simpleName)

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun sendSms() {

    }

    private fun getRandomString(length: Int): String {
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    private fun getRandomKey() = getRandomString(15)

    fun confirmPhoneNumber(phoneNumber: String): Mono<String> {
        val requestData = PhoneConfirmationData(callPhone = phoneNumber)
        val msgKey = getRandomKey()

        return producer
            .sendMessage(TelecomNumbersForConfirmationTopic.NAME, msgKey, requestData)
            .flatMap {
                consumer.consumeMessages(TelecomNumbersForConfirmationAnswerTopic.NAME)
            }
            .filter { it.key() == msgKey }
            .toMono()
            .flatMap { answer ->

                answer.receiverOffset().commit()

                when (val data = answer.value()) {
                    is PhoneConfirmationData -> Mono.just(data.callPhone)
                    is Error -> Mono.error(ResponseStatusException(HttpStatus.OK, data.message))
                    else -> Mono.error(ResponseStatusException(HttpStatus.OK, "unknown error"))
                }

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
        val requestData = PhoneConfirmationData(callPhone = phoneNumber)
        val msgKey = getRandomKey()

        return producer
            .sendMessage(TelecomConfirmationsTopic.NAME, msgKey, requestData)
            .flatMap {
                consumer.consumeMessages(TelecomConfirmationsAnswerTopic.NAME)
            }
            .filter { it.key() == msgKey }
            .toMono()
            .flatMap { answer ->

                answer.receiverOffset().commit()

                when (val data = answer.value()) {
                    is PhoneConfirmationData -> Mono.just(data.checkStatus)
                    is Error -> Mono.error(ResponseStatusException(HttpStatus.OK, data.message))
                    else -> Mono.error(ResponseStatusException(HttpStatus.OK, "unknown error"))
                }
            }
    }

    fun isPhoneNumberConfirmedRest(phoneNumber: String): Mono<String> {
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