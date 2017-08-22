package com.modzo.eventstore.api

import com.modzo.eventstore.domain.DomainException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

import static org.springframework.http.ResponseEntity.status

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(ApiException)
    @ResponseBody
    ResponseEntity<Error> processValidationError(ApiException exception) {
        return status(exception.httpStatus).body(new Error(exception))
    }

    @ExceptionHandler(DomainException)
    @ResponseBody
    ResponseEntity<Error> handleDomainException(DomainException exception) {
        return status(HttpStatus.BAD_REQUEST).body(new Error(exception))
    }
}
