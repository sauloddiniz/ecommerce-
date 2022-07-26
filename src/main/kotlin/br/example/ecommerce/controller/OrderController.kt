package br.example.ecommerce.controller

import br.example.ecommerce.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import to.Order
import java.math.BigDecimal
import java.net.URI

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping
    fun getOlaMundo(): Order {
        val order: Order = Order("userId", "orderId", "Descrição", 10.0);
        return order;
    }

    @PostMapping
    fun sendOrder(@RequestBody order:Order): ResponseEntity<Void> {
        orderService.createOrderProducer(order);
        return ResponseEntity.created(URI.create(order.orderId.toString())).build();
    }
}