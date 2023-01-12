package com.pdn.orderservice.testutills;

import com.pdn.orderservice.dto.OrderLineItemDto;
import com.pdn.orderservice.dto.OrderRequest;
import com.pdn.orderservice.dto.OrderResponse;

import java.math.BigDecimal;

public class TestData {
    public static OrderRequest orderRequest;
    public static OrderLineItemDto orderLineItemDto;
    public static OrderLineItemDto orderLineItemDtoWithoutSkuCode;
    public static OrderLineItemDto orderLineItemDtoWithoutPrice;
    public static OrderLineItemDto orderLineItemDtoWithoutQuantity;
    public static OrderResponse orderResponse;
    public static BigDecimal price;
    public static int quantity;
    public static String skuCode;

    static {
        price = BigDecimal.TEN;
        quantity = 12;
        skuCode = "sku1234";
        orderRequest = new OrderRequest();

        orderLineItemDto = new OrderLineItemDto();
        orderLineItemDto.setPrice(price);
        orderLineItemDto.setQuantity(quantity);
        orderLineItemDto.setSkuCode(skuCode);

        orderLineItemDtoWithoutSkuCode = new OrderLineItemDto();
        orderLineItemDtoWithoutSkuCode.setQuantity(quantity);
        orderLineItemDtoWithoutSkuCode.setPrice(price);

        orderLineItemDtoWithoutPrice = new OrderLineItemDto();
        orderLineItemDtoWithoutPrice.setSkuCode(skuCode);
        orderLineItemDtoWithoutPrice.setQuantity(quantity);

        orderLineItemDtoWithoutQuantity = new OrderLineItemDto();
        orderLineItemDtoWithoutQuantity.setSkuCode(skuCode);
        orderLineItemDtoWithoutQuantity.setPrice(price);


        orderResponse = new OrderResponse();
    }
}
