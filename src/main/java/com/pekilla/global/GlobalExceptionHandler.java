package com.pekilla.global;

import com.pekilla.category.exception.CategoryNotFoundException;
import com.pekilla.post.exception.PostNotFoundException;
import com.pekilla.customer.exception.CustomerNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getGlobal();

    private void log(String message, Exception ex) {
        logger.log(Level.WARNING, message, ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleConstraintViolationException(ConstraintViolationException ex) {
        log(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
    public Object handleUserNotFoundException(CustomerNotFoundException ex) {
        log(ex.getMessage(), ex);
        return "User not found";
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Post not found")
    public Object handlePostNotFoundException(PostNotFoundException ex) {
        log(ex.getMessage(), ex);
        return "Post not found";
    }

    // TO CHANGE AFTER DEV
    @Deprecated
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleException(Exception ex) {
        log(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleCategoryNotFoundException(CategoryNotFoundException ex) {
        log(ex.getMessage(), ex);
        return ex.getMessage();
    }
}
