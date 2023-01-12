package com.pdn.orderservice.controller;

import com.pdn.orderservice.dto.OrderRequest;
import com.pdn.orderservice.service.OrderService;
import com.pdn.orderservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static com.pdn.orderservice.testutills.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @MockBean
    OrderService orderService;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGiveStatusOkWhenOrderIsPlaced() throws Exception {
        orderRequest.setOrderLineItemsDtoList(Collections.singletonList(orderLineItemsDto));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeOrder");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(orderRequest));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void shouldBeAbleToPlaceAOrderByInvokingOrderService() throws Exception {
        orderRequest.setOrderLineItemsDtoList(Collections.singletonList(orderLineItemsDto));
        Mockito.when(orderService.placeOrder(orderRequest)).thenReturn(orderResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeOrder");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(orderRequest));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isCreated());
        ArgumentCaptor<OrderRequest> orderRequestArgumentCaptor = ArgumentCaptor.forClass(OrderRequest.class);
        verify(orderService).placeOrder(orderRequestArgumentCaptor.capture());
        assertEquals(orderRequest, orderRequestArgumentCaptor.getValue());
    }


    @Test
    void shouldGiveErrorWhenOrderRequestDoesNotHaveAnyLineItems() throws Exception {
        orderRequest.setOrderLineItemsDtoList(Collections.emptyList());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeOrder");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(orderRequest));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(Constants.LIST_MUST_NOT_BE_EMPTY));

    }
    @Test
    void shouldGiveErrorWhenOrderRequestDoesNotHaveSkuCode() throws Exception {
        orderRequest.setOrderLineItemsDtoList(Collections.singletonList(orderLineItemsDtoWithoutSkuCode));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeOrder");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(orderRequest));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(Constants.SKUCODE_MUST_NOT_BE_EMPTY));

    }

    @Test
    void shouldGiveErrorWhenOrderRequestDoesNotHavePrice() throws Exception {
        orderRequest.setOrderLineItemsDtoList(Collections.singletonList(orderLineItemsDtoWithoutPrice));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeOrder");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(orderRequest));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(Constants.PRICE_MUST_NOT_BE_EMPTY));
    }

    @Test
    void shouldGiveErrorWhenOrderRequestDoesNotHaveQuantity() throws Exception {
        orderRequest.setOrderLineItemsDtoList(Collections.singletonList(orderLineItemsDtoWithoutQuantity));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeOrder");
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        requestBuilder.content(objectMapper.writeValueAsString(orderRequest));

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(Constants.QUNATITY_MUST_NOT_BE_EMPTY));
    }


}