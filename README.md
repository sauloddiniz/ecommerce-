# ecommerce-kafka
## Projeto base para criar producers String,Json e AVRO

## Como executar
### docker-compose up
### ./gradlew build 
### docker build . -t ecommerce &&  docker run -p 8080:8080 --network host ecommerce

### post para http://localhost:8080/order
```
{
    "userId": "12",
    "orderId": "3",
    "description": "descriptionItem",
    "value": 1000.0
}
```

## Comsumers em outros projetos





