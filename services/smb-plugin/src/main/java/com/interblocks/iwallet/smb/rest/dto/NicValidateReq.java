package com.interblocks.iwallet.smb.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NicValidateReq {

    @JsonProperty("idType")
    String idType;

    @JsonProperty("idNumber")
    String idNumber;

    @JsonProperty("cifId")
    String cifId;

    @JsonProperty("foracid")
    String foracid;

    public NicValidateReq(String nicType,String idNumber) {
        this.idType = nicType;
        this.idNumber = idNumber;
        this.cifId = "";
        this.foracid = "";
    }

//    {
//        "idType":"NICN",
//            "idNumber":"198023201538",
//            "cifId":"",
//            "foracid": ""
//    }
}
