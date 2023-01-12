package com.pdn.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.pdn.orderservice.util.Constants.LIST_MUST_NOT_BE_EMPTY;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @Valid
    @NotEmpty(message = LIST_MUST_NOT_BE_EMPTY)
    private List<OrderLineItemDto> orderLineItemDtoList;
}

