package com.interblocks.iwallet.smb.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.interblocks.iwallet.smb.model.ResponseDefault;
import com.interblocks.iwallet.smb.util.ResponseCreator;
import com.interblocks.webtools.core.exceptions.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    protected <T extends ResponseDefault, E extends ServiceException> ResponseEntity<T> handleServiceException(E ex) {
        printException(ex);
        return ResponseCreator.failedResponse((T) new ResponseDefault(), ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected <T extends ResponseDefault> ResponseEntity<T> handleException(ConstraintViolationException ex) {
        printException(ex);
        ResponseDefault responseDefault = new ResponseDefault();
        responseDefault.setResponseCode("VALIDATION_FAILURE");
        responseDefault.setDescription("Validation Failure");
        ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath().toString() + ": " + v.getMessage())
                .forEach(responseDefault::addResponseValuesItem);
        return new ResponseEntity<>((T) responseDefault, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        printException(ex);
        ResponseDefault responseDefault = null;
        if (ex.getRootCause() != null && ex.getRootCause().getClass() == InvalidFormatException.class) {
            InvalidFormatException ife = (InvalidFormatException) ex.getRootCause();
            responseDefault = new ResponseDefault();
            responseDefault.setResponseCode("INVALID_DATA");
            responseDefault.setDescription("Invalid Data");

            if (ife.getPath() != null && !ife.getPath().isEmpty()) {
                JsonMappingException.Reference ref = ife.getPath().get(0);
                responseDefault.addResponseValuesItem(ref.getFieldName() + ": " + ife.getValue());
            }
        }
        return this.handleExceptionInternal(ex, responseDefault, headers, status, request);
    }


    @ExceptionHandler(Exception.class)
    protected <T extends ResponseDefault> ResponseEntity<T> handleException(Exception ex) {
        printException(ex);
        return ResponseCreator.failedResponse((T) new ResponseDefault(), ex);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BaseApplicationException.class})
    public BaseErrorResponse handleApplicationExceptions(BaseApplicationException e) {
        BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
        baseErrorResponse.setErrors(e.getCommonErrorList());
        baseErrorResponse.setError(e.getMessage());
        return baseErrorResponse;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ExternalServiceErrorException.class})
    public BaseErrorResponse handleExternalApiError(ExternalServiceErrorException e) {
        BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
        baseErrorResponse.setError(e.getMessage());
        baseErrorResponse.setErrors(e.getCommonErrors());
        return baseErrorResponse;
    }

    private <E extends Exception> void printException(E ex) {
        log.info("Executing RestExceptionHandler.handleException");
        log.error(ex.getMessage());
        log.error("{} : ", ex.getClass().getSimpleName(), ex);
        log.info("Creating Response for RestExceptionHandler.handleException");
    }
}
