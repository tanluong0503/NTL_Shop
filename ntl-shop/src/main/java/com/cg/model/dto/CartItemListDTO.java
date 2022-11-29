package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CartItemListDTO {

    private Long id;
    private Long productId;
    private String title;
    private String avatar;
    private BigDecimal price;
    private int quantity;
    private BigDecimal amount;
}
