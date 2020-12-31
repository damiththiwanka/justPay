package com.interblocks.iwallet.smb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NICValidationRequest {

    @JsonProperty("nic")
    private String nic = null;

    @JsonProperty("mobile")
    private String mobile = null;

    @JsonProperty("crdNo")
    private String crdNo = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("userId")
    private String userId = null;

}
