package com.pdn.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

import static com.pdn.orderservice.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLineItemDto {
    @NotNull(message = SKUCODE_MUST_NOT_BE_EMPTY)
    @NotBlank(message = SKUCODE_MUST_NOT_BE_EMPTY)
    private String skuCode;
    @NotNull(message = PRICE_MUST_NOT_BE_EMPTY)
    private BigDecimal price;
    @NotNull(message = QUNATITY_MUST_NOT_BE_EMPTY)
    private Integer quantity;
}