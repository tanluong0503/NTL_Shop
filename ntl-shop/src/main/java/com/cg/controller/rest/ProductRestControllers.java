package com.cg.controller.rest;

import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductRestControllers {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> showListProduct() {
        List<ProductDTO> productDTOS = productService.findAllProductDTO();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {

        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoryDTO();

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreateProduct (@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        new ProductDTO().validate(productDTO, bindingResult);
        Product newProduct;
        try {

            if (bindingResult.hasErrors()) {
                return appUtil.mapErrorToResponse(bindingResult);
            }

             newProduct = productService.save(productDTO.toProduct());

            return new ResponseEntity<>(newProduct.topProductDTO(), HttpStatus.OK);

        }
        catch (Exception e){

            e.printStackTrace();

            return new ResponseEntity<>("Failed to add product", HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/search/{query}")
    public ResponseEntity<?> seachListProduct(@PathVariable String query) {
        List<ProductDTO> productDTOList = productService.findProductByValue(query);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> productDTO = productService.findByProductDTOId(id);

        if (productDTO.isPresent()) {
            return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate (@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        new ProductDTO().validate(productDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Product updateProduct = productService.save(productDTO.toProduct());

        return new ResponseEntity<>(updateProduct.topProductDTO(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> doDelete(@PathVariable Long productId) {
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {

            productService.softDelete(product.get());
            return new ResponseEntity<>("Xoá sản phẩm thành công", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Vui lòng thử lại", HttpStatus.BAD_REQUEST);
        }
    }

}

