package com.estoque.gerenciamentoestoque.dto.product;

import com.estoque.gerenciamentoestoque.dto.category.CategoryResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private CategoryResponseDTO category;
    private String description;
    private BigDecimal price;
    private Integer amount;
    private LocalDateTime registrationDate;

}
