package com.restsample.exception;

import com.restsample.data.dto.ValidationErrorDTO;
import com.restsample.message.MessageByLocaleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomErrorException extends RuntimeException{

    public CustomErrorException(){
    }

    public CustomErrorException(String message, Throwable cause){
        super(message, cause);
    }

}
