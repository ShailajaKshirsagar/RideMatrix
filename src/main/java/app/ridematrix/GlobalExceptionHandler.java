package app.ridematrix;

import app.ridematrix.exception.*;
import app.ridematrix.exception.IllegalArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation failed: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRegistrationNum.class)
    public ResponseEntity<String> invalidRegNum(InvalidRegistrationNum ex){
        log.warn("Invalid registration number: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    //To handle database Access Exception
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> HandleDatabaseException(DataAccessException ex) {
        log.error("Database access exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database operation failed. Please try again later.");
    }

    //To handle Illegal argument Exception
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguException(IllegalArgumentException ex){
        log.warn("Illegal argument: {}", ex.getMessage());
        return ResponseEntity.badRequest().body("Invalid input: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.warn("Method argument type mismatch: {}", ex.getMessage());
        String msg = "Invalid parameter type provided: " + ex.getMessage();
        return ResponseEntity.badRequest().body(msg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        log.warn("Missing request parameter: {}", ex.getMessage());
        String msg = "Required request parameter is missing" + ex.getMessage();
        return ResponseEntity.badRequest().body(msg);
    }

    //validation failures handle
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        log.warn("Constraint violation: {}", ex.getMessage());
        String message = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getMessage())  // this is correct
                .findFirst()
                .orElse("Validation failed");
        return ResponseEntity.badRequest().body("Validation error: " + message);
    }
}

