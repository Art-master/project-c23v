package com.core.app.consumers

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import reactor.core.Disposable
import reactor.core.publisher.Flux
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOffset
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.receiver.ReceiverRecord
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch

class MailConsumer(bootstrapServers: String) {

    private val logger = LoggerFactory.getLogger(MailConsumer::class.java.name)

    private val BOOTSTRAP_SERVERS = "localhost:9092"
    private val TOPIC = "demo-topic"

    private var receiverOptions: ReceiverOptions<Int, String>
    private var dateFormat: SimpleDateFormat

    init {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.CLIENT_ID_CONFIG] = "sample-consumer"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "sample-group"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = IntegerDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        receiverOptions = ReceiverOptions.create(props)
        dateFormat = SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy")
    }

    fun consumeMessages(topic: String?, latch: CountDownLatch): Disposable {
        val options: ReceiverOptions<Int, String> = receiverOptions.subscription(Collections.singleton(topic))
            .addAssignListener { partitions -> logger.debug("onPartitionsAssigned {}", partitions) }
            .addRevokeListener { partitions -> logger.debug("onPartitionsRevoked {}", partitions) }
        val kafkaFlux: Flux<ReceiverRecord<Int, String>> = KafkaReceiver.create(options).receive()

        return kafkaFlux.subscribe { record ->
            val offset: ReceiverOffset = record.receiverOffset()
            System.out.printf(
                "Received message: topic-partition=%s offset=%d timestamp=%s key=%d value=%s\n",
                offset.topicPartition(),
                offset.offset(),
                dateFormat.format(Date(record.timestamp())),
                record.key(),
                record.value()
            )
            offset.acknowledge()
            latch.countDown()
        }
    }
}