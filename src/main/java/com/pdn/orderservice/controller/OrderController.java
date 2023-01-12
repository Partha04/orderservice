package com.pdn.orderservice.controller;

import com.pdn.orderservice.dto.OrderRequest;
import com.pdn.orderservice.dto.OrderResponse;
import com.pdn.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableSpringDataWebSupport
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    ResponseEntity<OrderResponse> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
    }
}
