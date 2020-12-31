package com.interblocks.iwallet.smb.util;

import com.interblocks.iwallet.smb.model.ResponseDefault;
import com.interblocks.webtools.core.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseCreator {

    public static ResponseEntity<ResponseDefault> successfulResponse() {
        ResponseDefault responseDefault = new ResponseDefault();
        responseDefault.setResponseCode("00");
        responseDefault.setDescription("Success");
        return new ResponseEntity<>(responseDefault, getResponseByCode("00"));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> successfulResponse(T responseDefault) {
        responseDefault.setResponseCode("00");
        responseDefault.setDescription("Success");

        return new ResponseEntity<>(responseDefault, getResponseByCode("00"));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> successfulResponse(T responseDefault, String resCode) {
        responseDefault.setResponseCode(resCode);
        responseDefault.setDescription("Success");

        return new ResponseEntity<>(responseDefault, getResponseByCode(resCode));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> successfulResponse(T responseDefault, String resCode, String resDesc) {
        responseDefault.setResponseCode(resCode);
        responseDefault.setDescription(resDesc);

        return new ResponseEntity<>(responseDefault, getResponseByCode(resCode));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> successfulResponse(T responseDefault, String resCode, String resDesc, List<String> params) {
        responseDefault.setResponseCode(resCode);
        responseDefault.setDescription(resDesc);
        responseDefault.setResponseValues(params);
        return new ResponseEntity<>(responseDefault, getResponseByCode(resCode));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> failedResponse(T responseDefault) {
        responseDefault.setResponseCode("ER01");
        responseDefault.setDescription("Error");

        return new ResponseEntity<>(responseDefault, getResponseByCode("ER01"));
    }

    public static <T extends ResponseDefault, E extends ServiceException> ResponseEntity<T> failedResponse(T responseDefault, E se) {
        responseDefault.setResponseCode(se.getErrorCode().orElse("ER01"));
        responseDefault.setDescription(se.getErrorDescription().orElse("Error"));
        responseDefault.setResponseValues(se.getErrorValues().orElse(new ArrayList<>()));

        return new ResponseEntity<>(responseDefault, getResponseByCode(responseDefault.getResponseCode()));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> failedResponse(T responseDefault, Exception ex) {
        responseDefault.setResponseCode("ER01");
        responseDefault.setDescription("Error");

        return new ResponseEntity<>(responseDefault, getResponseByCode(responseDefault.getResponseCode()));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> failedResponse(T responseDefault, String resCode, String resDesc) {
        responseDefault.setResponseCode(resCode);
        responseDefault.setDescription(resDesc);

        return new ResponseEntity<>(responseDefault, getResponseByCode(resCode));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> failedResponse(T responseDefault, String resCode, String resDesc, List<String> params) {
        responseDefault.setResponseCode(resCode);
        responseDefault.setDescription(resDesc);
        responseDefault.setResponseValues(params);
        return new ResponseEntity<>(responseDefault, getResponseByCode(resCode));
    }

    public static <T extends ResponseDefault> ResponseEntity<T> failedResponse(T responseDefault, String resCode, List<String> params) {
        responseDefault.setResponseCode(resCode);
        responseDefault.setResponseValues(params);
        return new ResponseEntity<>(responseDefault, getResponseByCode(resCode));
    }

    private static HttpStatus getResponseByCode(String code) {
        if (code != null) {
            switch (code) {
                case "00":
                    return HttpStatus.OK;
                case "01":
                    return HttpStatus.CREATED;
                default:
                    return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
