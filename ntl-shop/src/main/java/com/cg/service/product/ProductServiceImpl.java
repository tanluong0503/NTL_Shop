package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
         productRepository.deleteById(id);
    }

    @Override
    public void softDelete(Product product) {
        product.setDeleted(true);
        productRepository.save(product);
    }


    @Override
    public List<ProductDTO> findAllProductDTO() {
        return productRepository.findALlProdutDTO();
    }

    @Override
    public Optional<ProductDTO> findByProductDTOId(Long id) {
        return productRepository.findByProductDTOId(id);
    }

    @Override
    public List<ProductDTO> findProductByValue(String query) {
        return productRepository.findProductValue(query);
    }

    @Override
    public Optional<ProductDTO> findByIdProductDTO(Long id) {
        return productRepository.findByIdProductDTO(id);
    }

}
