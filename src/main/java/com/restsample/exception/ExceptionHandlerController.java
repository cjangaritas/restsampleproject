package com.restsample.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restsample.constants.RestSampleConstants;
import com.restsample.data.dto.ValidationErrorDTO;
import com.restsample.message.MessageByLocaleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
                            "" /* ****messageManagerService.getMessage(RestSampleConstants.UNEXPECTED_ERROR_MESSAGE)**** */,
                            "Default error message"));

//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            ObjectMapper jsonmapper = new ObjectMapper();
//            String strJsonError = RestSampleConstants.UNEXPECTED_ERROR_MESSAGE;
//            try {
//                strJsonError = jsonmapper.writeValueAsString(validationErrorDTO);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
            exception.printStackTrace();
//            return new ResponseEntity<>(strJsonError, headers, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(validationErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
