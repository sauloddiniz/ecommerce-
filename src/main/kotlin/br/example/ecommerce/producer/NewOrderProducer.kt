package producer

import br.example.dto.OrderAvro
import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import to.Order

class NewOrderProducer(val order: Order) {

    private val orderSerializerAvroClass: String = "io.confluent.kafka.serializers.KafkaAvroSerializer";
    private val orderSerializerClass: String = "serializers.OrderSerializar";
    private val emailSerializerClass: String = "org.apache.kafka.common.serialization.StringSerializer";

    fun createProducer() {

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

        val callback = Callback { data, exception ->
            if (exception != null) {
                exception.printStackTrace();
            }
            print("response data: ${data.partition()} ${data.topic()} ${data.timestamp()} ${data.serializedValueSize()}")
        }

        producerOrderAvro.send(newOrderProRecordAvro, callback).get();
        producerOrder.send(newOrderProRecord, callback).get();
        producerEmail.send(emailNewOrderProRecord, callback).get();
    }
}