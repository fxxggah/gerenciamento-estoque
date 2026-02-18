package com.estoque.gerenciamentoestoque.controller;

import com.estoque.gerenciamentoestoque.dto.category.CategoryRequestDTO;
import com.estoque.gerenciamentoestoque.dto.category.CategoryResponseDTO;
import com.estoque.gerenciamentoestoque.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

}
