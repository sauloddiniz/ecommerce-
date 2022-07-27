package br.example.ecommerce.serializers

import com.fasterxml.jackson.databind.json.JsonMapper
import org.apache.kafka.common.serialization.Serializer
import to.Order

class OrderSerializar : Serializer<Order> {

    val jsonMapper: JsonMapper = JsonMapper();

    override fun serialize(topic: String?, data: Order?): ByteArray? {
        return jsonMapper.writeValueAsBytes(data);
    };

    override fun close() {};

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {};
}
