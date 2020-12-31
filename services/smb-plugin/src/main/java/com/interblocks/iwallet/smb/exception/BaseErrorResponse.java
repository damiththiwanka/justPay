package com.interblocks.iwallet.smb.exception;

import com.interblocks.webtools.broker.model.iadmin.merchant.shared.IAdminCommonError;
import lombok.Data;

import java.util.List;

@Data
public class BaseErrorResponse {
    private String error;
    private List<IAdminCommonError> errors;
}
