package com.akib.oms.dto;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class UpdateOrder {

    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
