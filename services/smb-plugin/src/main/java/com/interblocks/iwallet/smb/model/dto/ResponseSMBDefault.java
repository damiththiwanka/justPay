package com.interblocks.iwallet.smb.model.dto;

import lombok.Data;

@Data
public class ResponseSMBDefault {
    int status;
    Object entity;

    public ResponseSMBDefault status(int status){
        this.status=status;
        return this;
    }

    public ResponseSMBDefault entity(Object entity){
        this.entity=entity;
        return this;
    }

}
