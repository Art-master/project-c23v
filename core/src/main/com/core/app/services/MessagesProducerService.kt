package com.core.app.services

import c23v.domain.entities.Message
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import javax.annotation.PostConstruct
import kotlin.collections.HashMap

@Service("producer")
class MessagesProducerService {

    @Value("\${BOOTSTRAP_SERVERS}")
    private lateinit var bootstrapServers: String

    private lateinit var sender: KafkaSender<String, Message>
    private val log: Logger = LoggerFactory.getLogger(MessagesProducerService::class.java.name)

    @PostConstruct
    private fun initSender() {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.CLIENT_ID_CONFIG] = "telecom-producer"
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        //props[JsonDeserializer.VALUE_DEFAULT_TYPE] = "c23v.domain.entities.Message"
        //props[JsonDeserializer.USE_TYPE_INFO_HEADERS] = false
        props[JsonDeserializer.TRUSTED_PACKAGES] = "*"
        val senderOptions = SenderOptions.create<String, Message>(props)
        sender = KafkaSender.create(senderOptions)
    }

    fun sendMessage(topic: String, key: String?, value: Message, count: Int = 1): Flux<SenderResult<String>> {
        return sender.send(Flux.range(1, count)
            .map { i ->
                SenderRecord.create(ProducerRecord(topic, key ?: i.toString(), value), i.toString())
            })
            .doOnError { e: Throwable? ->
                log.error("Send failed", e)
            }
    }

    fun sendMessage(topic: String, value: Message, count: Int = 1): Flux<SenderResult<String>> {
        return sendMessage(topic, null, value, count)
    }

    fun close() {
        sender.close()
    }
}