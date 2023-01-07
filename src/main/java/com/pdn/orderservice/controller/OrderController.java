package com.pdn.orderservice.controller;

import com.pdn.orderservice.dto.OrderRequest;
import com.pdn.orderservice.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class OrderController {
    @PostMapping("/placeOrder")
    ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) throws URISyntaxException {
        return ResponseEntity.created(new URI("/placeOrder")).build();
    }
}
