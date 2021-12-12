package com.core.app.services

import c23v.domain.entities.Message
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.receiver.ReceiverRecord
import java.util.*
import javax.annotation.PostConstruct

@Service("consumer")
class MessagesConsumerService() {

    @Value("\${BOOTSTRAP_SERVERS}")
    private lateinit var bootstrapServers: String

    private val logger = LoggerFactory.getLogger(MessagesConsumerService::class.java.name)

    private lateinit var receiverOptions: ReceiverOptions<String, Message>

    @PostConstruct
    private fun initReceiverOptions() {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.CLIENT_ID_CONFIG] = "core-consumer"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "sample-group"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        //props[JsonDeserializer.VALUE_DEFAULT_TYPE] = "c23v.domain.entities.Message"
        //props[JsonDeserializer.USE_TYPE_INFO_HEADERS] = false
        props[JsonDeserializer.TRUSTED_PACKAGES] = "*"

        receiverOptions = ReceiverOptions.create(props)
    }

    fun consumeMessages(topic: String): Flux<ReceiverRecord<String, Message>> {
        val options: ReceiverOptions<String, Message> = receiverOptions.subscription(Collections.singleton(topic))
            .addAssignListener { partitions -> logger.debug("onPartitionsAssigned {}", partitions) }
            .addRevokeListener { partitions -> logger.debug("onPartitionsRevoked {}", partitions) }

        return KafkaReceiver.create(options).receive()
    }
}