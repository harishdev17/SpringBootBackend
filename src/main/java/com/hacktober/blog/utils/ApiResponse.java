package com.hacktober.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String internalCode;
    private String message;
    private T data;
    private List<String> errors;
    private String timestamp;

    // Custom constructor for creating responses
    public ApiResponse(int status, String internalCode, String message, T data, List<String> errors) {
        this.status = status;
        this.internalCode = internalCode;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    // Success helper with data
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                null,
                message,
                data,
                null
        );
    }

    // Success helper without data
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                null,
                message,
                null,
                null
        );
    }

    // Error helper with exception
    public static <T> ApiResponse<T> error(Exception ex, String internalCode, HttpStatus status, T data, List<String> errors) {
        String errorMessage = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";
        List<String> errorList = errors != null ? errors : List.of(errorMessage);
        return new ApiResponse<>(
                status.value(),
                internalCode,
                errorMessage,
                data,
                errorList
        );
    }

    // Simpler error helper
    public static <T> ApiResponse<T> error(String message, String internalCode, HttpStatus status, T data) {
        return new ApiResponse<>(
                status.value(),
                internalCode,
                message,
                data,
                List.of(message)
        );
    }

    // Error helper without data
    public static <T> ApiResponse<T> error(String message, String internalCode, HttpStatus status) {
        return error(message, internalCode, status, null);
    }
}