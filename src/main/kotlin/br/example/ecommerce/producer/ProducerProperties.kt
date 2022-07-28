package br.example.ecommerce.producer

open class ProducerProperties(private val serializableClass: String) {

    open fun createProperties(): MutableMap<String, String> {
        val properties = mutableMapOf<String, String>();
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer";
        properties["value.serializer"] = serializableClass;
        properties["bootstrap.servers"] = "kafka1:19092";
        return properties;
    }

    open fun createPropertiesAvro(): MutableMap<String, String> {
        val properties = mutableMapOf<String, String>();
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer";
        properties["value.serializer"] = serializableClass;
        properties["bootstrap.servers"] = "kafka1:19092";
        properties["schema.registry.url"] = "http://kafka-schema-registry:8081";
        return properties;
    }

}