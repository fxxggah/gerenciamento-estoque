package com.estoque.gerenciamentoestoque.exception;

import com.estoque.gerenciamentoestoque.dto.error.ApiError;
import com.estoque.gerenciamentoestoque.dto.error.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 🔹 400 - Erro de validação (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(
                    error.getField(),
                    error.getDefaultMessage() != null
                            ? error.getDefaultMessage()
                            : "Valor inválido"
            );
        }

        ValidationError validationError = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                errors
        );

        return ResponseEntity.badRequest().body(validationError);
    }

    // 🔹 404 - Recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // 🔹 400 - Regra de negócio
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusiness(BusinessException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );

        return ResponseEntity.badRequest().body(error);
    }

    // 🔹 400 - JSON mal formatado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleInvalidJson(HttpMessageNotReadableException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "JSON inválido ou mal formatado"
        );

        return ResponseEntity.badRequest().body(error);
    }

    // 🔹 400 - Tipo de parâmetro inválido (ex: id=abc)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Parâmetro inválido: " + ex.getName()
        );

        return ResponseEntity.badRequest().body(error);
    }

    // 🔹 409 - Violação de integridade (constraint no banco)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex) {

        log.error("Erro de integridade no banco", ex);

        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Violação de integridade dos dados"
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // 🔹 500 - Erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {

        log.error("Erro inesperado", ex);

        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}