package com.pdn.orderservice.service;

import com.pdn.orderservice.dto.OrderLineItemDto;
import com.pdn.orderservice.dto.OrderRequest;
import com.pdn.orderservice.dto.OrderResponse;
import com.pdn.orderservice.model.Order;
import com.pdn.orderservice.model.OrderLineItem;
import com.pdn.orderservice.repository.OrderRepository;
import com.pdn.orderservice.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    ModelMapper modelMapper = new ModelMapper();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        List<OrderLineItemDto> orderLineItemDtoList = orderRequest.getOrderLineItemDtoList();
        List<OrderLineItem> orderLineItems = orderLineItemDtoList.stream()
                .map(orderLineItemDto -> modelMapper.map(orderLineItemDto, OrderLineItem.class))
                .collect(Collectors.toList());
        Order order = Order.builder().orderLineItemList(orderLineItems).build();
        order.setOrderLineItemList(orderLineItems);
        orderRepository.save(order);
        return OrderResponse.builder().orderId(order.getId()).message(Constants.ORDER_PLACED_SUCCESSFULLY).build();
    }
}
