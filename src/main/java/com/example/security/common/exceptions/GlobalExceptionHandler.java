package com.example.security.common.exceptions;


import com.example.security.common.responses.ResponseWithDetails;
import com.example.security.common.responses.ResponseWithoutDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseWithDetails responseWithDetails;
    private final ResponseWithoutDetails responseWithoutDetails;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWithDetails> handleValidationException(MethodArgumentNotValidException e){
        responseWithDetails.setTimesTamps(LocalDateTime.now());
        responseWithDetails.setMessage("validation error");
        responseWithDetails.setStatus("422");
        Map<String,Object> errors = e.getBindingResult()
                                                         .getFieldErrors()
                                                          .stream()
                                                          .collect(
                                                                  HashMap::new,
                                                                  (map,fieldError) -> map.put(fieldError.getField(),fieldError.getDefaultMessage()),
                                                                  (map,map2) -> map.putAll(map2)
                                                          );
        responseWithDetails.setDetails(errors)  ;
        return ResponseEntity.badRequest().body(responseWithDetails);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseWithoutDetails> handleNoSuchElementException(NoSuchElementException e){

        responseWithoutDetails.setTimesTamps(LocalDateTime.now());
        responseWithoutDetails.setStatus("404");
        responseWithoutDetails.setMessage("no such element exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseWithoutDetails);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseWithDetails> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        responseWithDetails.setTimesTamps(LocalDateTime.now());
        responseWithDetails.setMessage("Data integrity violation");
        responseWithDetails.setStatus("409");
        Map<String,Object> errors = new HashMap<>();
        String exceptionMessage = dataIntegrityViolationException.getRootCause().getMessage();
        errors.put("duplicate key", exceptionMessage);
        responseWithDetails.setDetails(errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseWithDetails);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseWithDetails> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        responseWithDetails.setTimesTamps(LocalDateTime.now());
        responseWithDetails.setMessage("Illegal argument");
        responseWithDetails.setStatus("400");
        Map<String,Object> errors = new HashMap<>();
        errors.put("Error", illegalArgumentException.getMessage());
        responseWithDetails.setDetails(errors);
        return ResponseEntity.badRequest().body(responseWithDetails);
    }
}
