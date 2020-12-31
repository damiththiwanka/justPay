package com.interblocks.iwallet.smb.exception;

import com.interblocks.webtools.broker.model.iadmin.merchant.shared.IAdminCommonError;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExternalServiceErrorException extends RuntimeException {
    private List<IAdminCommonError> commonErrors;
    private String errorCode;

    public ExternalServiceErrorException(String message, List<IAdminCommonError> commonErrors) {
        super(message);
        this.commonErrors = commonErrors;
    }
}

