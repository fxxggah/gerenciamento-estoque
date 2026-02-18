package com.estoque.gerenciamentoestoque.controller;

import com.estoque.gerenciamentoestoque.dto.product.ProductRequestDTO;
import com.estoque.gerenciamentoestoque.dto.product.ProductResponseDTO;
import com.estoque.gerenciamentoestoque.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Cadastrar um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok((productService.findById(productId)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> findByCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productService.findByCategory(categoryId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("productId") Long productId, @Valid @RequestBody ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.update(productId, dto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

}
