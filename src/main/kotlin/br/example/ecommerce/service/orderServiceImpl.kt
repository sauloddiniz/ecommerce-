package br.example.ecommerce.service

import org.springframework.stereotype.Service
import br.example.ecommerce.producer.NewOrderProducer
import to.Order

@Service
class orderServiceImpl: OrderService {
    override fun createOrderProducer(order: Order) {
        NewOrderProducer(order).createProducer();
    }
}