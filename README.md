# ecommerce-kafka
## Projeto base para criar producers String,Json e AVRO

## Como executar
### /gradlew build
```
docker-compose up
```
```
docker build . -t ecommerce &&  docker run -p 8080:8080 --network host ecommerce
```
### post para http://localhost:8080/order
{
    "userId": "12",
    "orderId": "3",
    "description": "descriptionItem",
    "value": 1000.0
}
```

## Consumers em outros projetos
### [AVRO] (https://github.com/sauloddiniz/ecommerce-consumer-avro)
### [JSON] (https://github.com/sauloddiniz/ecommerce-consumer-json)
### [String] (https://github.com/sauloddiniz/ecommerce-consumer-string)





