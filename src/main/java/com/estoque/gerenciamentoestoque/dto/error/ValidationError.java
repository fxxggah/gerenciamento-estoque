package com.estoque.gerenciamentoestoque.dto.error;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ValidationError {

    private int status;
    private Map<String, String> errors;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ValidationError(int status, Map<String, String> errors) {
        this.status = status;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

}
