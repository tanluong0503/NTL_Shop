package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO implements Validator {

    private Long id;

    @NotBlank(message = "Product name is required!!")
    @Pattern(regexp = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+", message = "Product name contains only letters")
    private String productName;

    @Valid
    private CategoryDTO category;

    @NotNull(message = "Product quantity not null!!")
    @Min(value = 1, message = "Minimum quantity of one product")
    @Max(value = 999, message = "Quantity cannot exceed 999 products")
    private BigDecimal quantity;


    @NotNull(message = "Product name not null!!")
    @Min(value = 10000, message = "Minimum amount 10,000VND")
    @Max(value = 999999999, message = "Amount does not exceed 999,999,999VND")
    private BigDecimal price;

    @NotBlank(message = "Product description is required!!")
    private String description;

    @NotBlank(message = "Không được để trống ảnh sản phẩm!!")
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    private Date createdAt;

    private Date updatedAt;

    public ProductDTO(Long id, String productName, Category category, BigDecimal quantity, BigDecimal price,
                      String description, String image, Date createdAt, Date updatedAt) {
        this.id = id;
        this.productName = productName;
        this.category = category.categoryDTO();
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductDTO(Long id, String productName, Category category, BigDecimal quantity, BigDecimal price, String description, String image) {
        this.id = id;
        this.productName = productName;
        this.category = category.categoryDTO();
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Product toProduct() {
        return new Product()
                .setId(id)
                .setProductName(productName)
                .setCategory(category.toCategory())
                .setQuantity(quantity)
                .setPrice(price)
                .setDescription(description)
                .setImage(image);
    }




    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
