package com.interblocks.iwallet.smb.util.jpy;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.interblocks.imobile.api.util.IMobileProperties;


public class JustPayDeleteUserRequest {

    @JsonProperty("MerchantID")
    private String merchantId;

    @JsonProperty("MerchantToken")
    private String merchantToken;

    @JsonProperty("ReferenceId")
    private String referenceId;

    @JsonProperty("CustomerToken")
    private String customerToken;

    @JsonProperty("AccountRef")
    private String accountRef;

    @JsonProperty("TimeStamp")
    private String timeStamp;


    public JustPayDeleteUserRequest() {
    }

//    public JustPayDeleteUserRequest(IMobileProperties iMobileProperties, String referenceId, String customerToken, String accountRef) {
//        this.merchantId = iMobileProperties.getJustPayProperties().getProperty("merchantId");
//        this.merchantToken = iMobileProperties.getJustPayProperties().getProperty("merchantToken");
//        this.timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
//        this.customerToken = customerToken;
//        this.accountRef = accountRef;
//        this.referenceId = referenceId;
//    }




}
