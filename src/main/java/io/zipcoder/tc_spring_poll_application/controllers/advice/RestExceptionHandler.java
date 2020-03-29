package io.zipcoder.tc_spring_poll_application.controllers.advice;

import io.zipcoder.tc_spring_poll_application.dtos.error.ErrorDetail;
import io.zipcoder.tc_spring_poll_application.dtos.error.ValidationError;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request){
        ErrorDetail error = new ErrorDetail();
        error.setTitle("Not Found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setDetail("Not today!");
        error.setTimeStamp(new Date().getTime());
        error.setDeveloperMessage("This came from " + rnfe.getCause());

        return new ResponseEntity<>(error.getDetail(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
        ErrorDetail error = new ErrorDetail();
        error.setTitle("Not Found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setDetail("Not today!");
        error.setTimeStamp(new Date().getTime());
        error.setDeveloperMessage("This came from " + manve.getCause());

        // TODO fix this laster
        List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors) {

            List<ValidationError> validationErrorList = error.getErrorMap().get(fe.getField());
            if(validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                error.getErrorMap().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe,null));
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
