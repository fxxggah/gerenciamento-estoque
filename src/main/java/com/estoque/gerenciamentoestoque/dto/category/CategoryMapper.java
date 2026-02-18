package com.estoque.gerenciamentoestoque.dto.category;

import com.estoque.gerenciamentoestoque.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}
