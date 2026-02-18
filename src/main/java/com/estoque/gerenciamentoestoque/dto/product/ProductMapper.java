package com.estoque.gerenciamentoestoque.dto.product;

import com.estoque.gerenciamentoestoque.dto.category.CategoryResponseDTO;
import com.estoque.gerenciamentoestoque.entity.Category;
import com.estoque.gerenciamentoestoque.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCategory(category);
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        return product;
    }

    public static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                new CategoryResponseDTO(
                        product.getCategory().getId(),
                        product.getCategory().getName()
                ),
                product.getDescription(),
                product.getPrice(),
                product.getAmount(),
                product.getRegistrationDate()
        );
    }

}
