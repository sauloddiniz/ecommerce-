package br.example.ecommerce.service

import to.Order

interface OrderService {
    fun createOrderProducer(order: Order);
}