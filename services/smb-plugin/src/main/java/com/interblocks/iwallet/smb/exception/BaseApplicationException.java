package com.interblocks.iwallet.smb.exception;

import com.interblocks.webtools.broker.model.iadmin.merchant.shared.IAdminCommonError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseApplicationException extends RuntimeException {
    private String errorCode;
    private List<IAdminCommonError> commonErrorList = new ArrayList<>();

    public BaseApplicationException(String message) {
        super(message);
    }
}
