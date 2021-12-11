package com.core.app.messages

import c23v.domain.entities.Message
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import reactor.core.publisher.Flux
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import kotlin.collections.HashMap

class MessagesProducer(private val bootstrapServers: String) {

    private val sender = initSender()
    private val log: Logger = LoggerFactory.getLogger(MessagesProducer::class.java.name)

    private fun initSender(): KafkaSender<String, Message> {
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
        return KafkaSender.create(senderOptions)
    }

    fun sendMessage(topic: String, value: Message, count: Int = 1): Flux<SenderResult<String>> {
        return sender.send(Flux.range(1, count)
            .map { i ->
                SenderRecord.create(ProducerRecord(topic, i.toString(), value), i.toString())
            })
            .doOnError { e: Throwable? ->
                log.error("Send failed", e)
            }
    }

    fun close() {
        sender.close()
    }
}