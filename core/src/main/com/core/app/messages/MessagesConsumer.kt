package com.core.app.messages

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.receiver.ReceiverRecord
import java.util.*

class MessagesConsumer(private val bootstrapServers: String) {

    private val logger = LoggerFactory.getLogger(MessagesConsumer::class.java.name)

    private var receiverOptions = initReceiverOptions()

    private fun initReceiverOptions(): ReceiverOptions<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.CLIENT_ID_CONFIG] = "core-consumer"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "sample-group"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        return ReceiverOptions.create(props)
    }

    fun consumeMessages(topic: String): Flux<ReceiverRecord<String, String>> {
        val options: ReceiverOptions<String, String> = receiverOptions.subscription(Collections.singleton(topic))
            .addAssignListener { partitions -> logger.debug("onPartitionsAssigned {}", partitions) }
            .addRevokeListener { partitions -> logger.debug("onPartitionsRevoked {}", partitions) }

        return KafkaReceiver.create(options).receive()
    }
}