package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query ("SELECT new com.cg.model.dto.ProductDTO ( " +
            "p.id, " +
            "p.productName, " +
            "p.category, " +
            "p.quantity, " +
            "p.price, " +
            "p.description, " +
            "p.image, " +
            "p.createdAt, " +
            "p.updatedAt)" +
            "FROM Product AS p WHERE p.deleted = false ")
         List<ProductDTO> findALlProdutDTO();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO (p.id, p.productName,p.category, p.quantity, p.price, p.description, p.image, p.createdAt, p.updatedAt)  FROM Product AS p  WHERE p.deleted = false AND p.id = ?1 ")
    Optional<ProductDTO> findByProductDTOId(Long id);

    @Query("SELECT NEW com.cg.model.dto.ProductDTO ( " +
            "p.id, " +
            "p.productName, " +
            "p.category, " +
            "p.quantity, " +
            "p.price, " +
            "p.description, " +
            "p.image  )" +
            "FROM Product  p WHERE  " +
            " p.description like %?1% " +
            "or p.productName like %?1% ")
    List<ProductDTO> findProductValue(String query);


    @Query("SELECT NEW com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.productName, " +
            "p.category, " +
            "p.quantity, " +
            "p.price, " +
            "p.description, " +
            "p.image, " +
            "p.createdAt, " +
            "p.updatedAt)" +
            " FROM Product AS p  WHERE p.deleted = false AND p.id = ?1 ")
    Optional<ProductDTO> findByIdProductDTO(Long id);
}
