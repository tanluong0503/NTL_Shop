package com.cg.model;

import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private BigDecimal quantity;

    @Column(name = "product_price")
    private BigDecimal price;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",name = "images")
    private String image;

    @Column(name = "product_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public ProductDTO topProductDTO() {
        return new ProductDTO()
                .setId(id)
                .setProductName(productName)
                .setCategory(category.categoryDTO())
                .setQuantity(quantity)
                .setPrice(price)
                .setDescription(description)
                .setImage(image);
    }



}
