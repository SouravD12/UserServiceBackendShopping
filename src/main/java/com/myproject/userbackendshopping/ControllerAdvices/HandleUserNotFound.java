package com.myproject.userbackendshopping.ControllerAdvices;

import com.myproject.userbackendshopping.Exceptions.UserNotFoundException;
import com.myproject.userbackendshopping.dtos.ExceptionsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleUserNotFound {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionsDto>userNotFound(UserNotFoundException userNotFoundException){
        ExceptionsDto dto = new ExceptionsDto();
        dto.setMessage(userNotFoundException.getMessage());
        dto.setDetails("Please check your email id and retry");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

}
