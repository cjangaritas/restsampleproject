package com.restsample.exception;

import com.restsample.constants.RestSampleConstants;
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
public class ExceptionHandlerController {

    @Autowired
    private MessageByLocaleService messageManagerService;


    @ExceptionHandler( RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ValidationErrorDTO> handleError(HttpServletRequest req, Exception exception) {
        ValidationErrorDTO validationErrorDTO =
                new ValidationErrorDTO("500",
                        StringUtils.defaultIfBlank(
                                 messageManagerService.getMessage(RestSampleConstants.UNEXPECTED_ERROR_MESSAGE) ,
                                "Default error message"));

        exception.printStackTrace();
        return new ResponseEntity<>(validationErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
