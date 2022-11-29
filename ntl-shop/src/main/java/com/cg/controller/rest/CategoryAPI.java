package com.cg.controller.rest;


import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.repository.CategoryRepository;
import com.cg.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllCategory(){
        List<CategoryDTO> categories = categoryService.findAllCategoryDTO();
        return  new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
