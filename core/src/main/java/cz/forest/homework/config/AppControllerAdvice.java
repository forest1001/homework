package cz.forest.homework.config;

import cz.forest.homework.exception.HomeworkBaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(HomeworkBaseRuntimeException.class)
    public ResponseEntity<HttpErrorResponseMessge> handleNullPointerExceptions(HomeworkBaseRuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new HttpErrorResponseMessge(status,e.getMessage()), status);
    }
}
