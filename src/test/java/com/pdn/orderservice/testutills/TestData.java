package com.pdn.orderservice.testutills;

import com.pdn.orderservice.dto.OrderLineItemsDto;
import com.pdn.orderservice.dto.OrderRequest;
import com.pdn.orderservice.dto.OrderResponse;

import java.math.BigDecimal;

public class TestData {
    public static OrderRequest orderRequest;
    public static OrderLineItemsDto orderLineItemsDto;
    public static OrderLineItemsDto orderLineItemsDtoWithoutSkuCode;
    public static OrderLineItemsDto orderLineItemsDtoWithoutPrice;
    public static OrderLineItemsDto orderLineItemsDtoWithoutQuantity;
    public static OrderResponse orderResponse;
    public static BigDecimal ten;
    public static int quantity;
    public static String skuCode;

    static {
        ten = BigDecimal.TEN;
        quantity = 12;
        skuCode = "sku1234";
        orderRequest = new OrderRequest();

        orderLineItemsDto = new OrderLineItemsDto();
        orderLineItemsDto.setPrice(ten);
        orderLineItemsDto.setQuantity(quantity);
        orderLineItemsDto.setSkuCode(skuCode);

        orderLineItemsDtoWithoutSkuCode = new OrderLineItemsDto();
        orderLineItemsDtoWithoutSkuCode.setQuantity(quantity);
        orderLineItemsDtoWithoutSkuCode.setPrice(ten);

        orderLineItemsDtoWithoutPrice = new OrderLineItemsDto();
        orderLineItemsDtoWithoutPrice.setSkuCode(skuCode);
        orderLineItemsDtoWithoutPrice.setQuantity(quantity);

        orderLineItemsDtoWithoutQuantity = new OrderLineItemsDto();
        orderLineItemsDtoWithoutQuantity.setSkuCode(skuCode);
        orderLineItemsDtoWithoutQuantity.setPrice(ten);


        orderResponse = new OrderResponse();
    }
}
