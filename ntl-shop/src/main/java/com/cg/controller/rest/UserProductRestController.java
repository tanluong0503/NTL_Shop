package com.cg.controller.rest;

import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/user/products")
public class UserProductRestController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> showListProduct() {
        List<ProductDTO> products = productService.findAllProductDTO();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {
        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoryDTO();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> productDTO = productService.findByIdProductDTO(id);
        if (productDTO.isPresent()) {
            return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchListProduct(@PathVariable String query) {
        List<ProductDTO> productDTOList = productService.findProductByValue(query);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
}