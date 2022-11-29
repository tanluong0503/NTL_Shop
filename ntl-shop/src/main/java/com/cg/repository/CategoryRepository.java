package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Query("SELECT new com.cg.model.dto.CategoryDTO (" +
            "c.id, " +
            "c.name " +
            ") " +
            "FROM Category AS c")
    List<CategoryDTO> findAllCategoryDTO();

    @Query("SELECT new com.cg.model.dto.CategoryDTO (" +
            "c.id, " +
            "c.name " +
            ") " +
            "FROM Category AS c ")
    Optional<CategoryDTO> finAllCategoryDTOById(Long id);
}
