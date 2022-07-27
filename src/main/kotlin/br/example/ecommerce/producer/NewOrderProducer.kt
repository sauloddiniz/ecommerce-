package br.example.ecommerce.producer

import br.example.dto.OrderAvro
import br.example.ecommerce.producer.ProducerProperties
import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import to.Order

class NewOrderProducer(val order: Order) {

    private val orderSerializerAvroClass: String = "io.confluent.kafka.serializers.KafkaAvroSerializer";
    private val orderSerializerClass: String = "br.example.ecommerce.serializers.OrderSerializar";
    private val emailSerializerClass: String = "org.apache.kafka.common.serialization.StringSerializer";

    fun createProducer() {

        print("response data")
        print(order)

        val orderAvro: OrderAvro = OrderAvro(order.userId, order.orderId, order.description, order.value);

        val newOrderProRecordAvro: ProducerRecord<String, OrderAvro> =
            ProducerRecord("ECOMMERCE_AVRO", orderAvro.userId, orderAvro);

        val newOrderProRecord: ProducerRecord<String, Order> =
            ProducerRecord("ECOMMERCE_NEW_ORDER", order.userId, order);

        val emailNewOrderProRecord: ProducerRecord<String, String> =
            ProducerRecord("ECOMMERCE_EMAIL", order.orderId, " thanks for the purchase ${order.description}");

        val producerOrderAvro =
            KafkaProducer<String, OrderAvro>(ProducerProperties(orderSerializerAvroClass).createPropertiesAvro() as Map<String, Any>?)
        val producerOrder =
            KafkaProducer<String, Order>(ProducerProperties(orderSerializerClass).createProperties() as Map<String, Any>?)
        val producerEmail =
            KafkaProducer<String, String>(ProducerProperties(emailSerializerClass).createProperties() as Map<String, Any>?)

        print("response data")
        print(producerOrder)
        val callback = Callback { data, exception ->
            exception?.printStackTrace()
            print("response data: ${data.partition()} ${data.topic()} ${data.timestamp()} ${data.serializedValueSize()}")
        }

        producerOrderAvro.send(newOrderProRecordAvro, callback).get();
        producerOrder.send(newOrderProRecord, callback).get();
        producerEmail.send(emailNewOrderProRecord, callback).get();
    }
}