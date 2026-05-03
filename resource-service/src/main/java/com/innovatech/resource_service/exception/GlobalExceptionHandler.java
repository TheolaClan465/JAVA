package com.innovatech.resource_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.innovatech.resource_service.model.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorDTO createErrorDTO(int status, Date date, Map<String, String> errorMap){

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setStatus(status);
        errorDTO.setDate(date);
        errorDTO.setErrors(errorMap);

        return errorDTO;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationFields(MethodArgumentNotValidException exception){
        
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(400)
                .body(this.createErrorDTO(HttpStatus.BAD_REQUEST.value(),new Date(),errorMap));
    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ErrorDTO> handleProjectException(ResourceException exception){
        if (exception.getMessage().contains("no se encuentra en la base de datos")){
            Map<String, String> errorMap = Collections.singletonMap("Recurso no encontrada", exception.getMessage());
            return ResponseEntity.status(404)
                    .body(this.createErrorDTO(HttpStatus.CONFLICT.value(), new Date(), errorMap));
        }else{

            Map<String, String> errorMap = Collections.singletonMap("Recurso existe", exception.getMessage());
            return ResponseEntity.status(409)
                    .body(this.createErrorDTO(HttpStatus.CONTINUE.value(), new Date(), errorMap));

        }

    }
    
}
