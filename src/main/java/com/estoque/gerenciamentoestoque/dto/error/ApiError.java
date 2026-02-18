package com.estoque.gerenciamentoestoque.dto.error;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApiError {

    private int status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
