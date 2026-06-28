package com.akib.oms.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
