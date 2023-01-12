package com.pdn.orderservice.service;

import com.pdn.orderservice.dto.OrderResponse;
import com.pdn.orderservice.model.Order;
import com.pdn.orderservice.model.OrderLineItem;
import com.pdn.orderservice.repository.OrderRepository;
import com.pdn.orderservice.testutills.PostgresTestContainer;
import com.pdn.orderservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.pdn.orderservice.testutills.TestData.orderLineItemDto;
import static com.pdn.orderservice.testutills.TestData.orderRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest extends PostgresTestContainer {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void shouldPlaceOrderWhenOrderIsValid() {
        orderRequest.setOrderLineItemDtoList(List.of(orderLineItemDto));
        OrderResponse response = orderService.placeOrder(orderRequest);
        assertNotNull(response.getOrderId());
        assertEquals(Constants.ORDER_PLACED_SUCCESSFULLY, response.getMessage());
    }

    @Test
    void shouldStoreTheOrderInTheDatabase() {
        orderRequest.setOrderLineItemDtoList(List.of(orderLineItemDto));
        OrderResponse response = orderService.placeOrder(orderRequest);
        Optional<Order> orderOptional = orderRepository.findById(response.getOrderId());
        assertTrue(orderOptional.isPresent());
        Order order = orderOptional.get();
        OrderLineItem lineItems = order.getOrderLineItemList().get(0);
        assertEquals(orderLineItemDto.getPrice().intValue(), lineItems.getPrice().intValue());
        assertEquals(orderLineItemDto.getSkuCode(), lineItems.getSkuCode());
        assertEquals(orderLineItemDto.getQuantity(), lineItems.getQuantity());
    }
}