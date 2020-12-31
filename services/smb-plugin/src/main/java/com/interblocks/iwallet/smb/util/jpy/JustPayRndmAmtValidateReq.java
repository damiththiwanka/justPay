package com.interblocks.iwallet.smb.util.jpy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

//import com.interblocks.imobile.api.util.IMobileProperties;

public class JustPayRndmAmtValidateReq {

    @JsonProperty("MerchantID")
    private String merchantId;

    @JsonProperty("MerchantToken")
    private String merchantToken;

    @JsonProperty("ReferenceId")
    private String referenceId;

    @JsonProperty("CustomerNic")
    private String customerNic;

    @JsonProperty("TimeStamp")
    private String timeStamp;

    @JsonProperty("RandomAmt")
    private String randomAmt;

    public JustPayRndmAmtValidateReq() {
    }

//    public JustPayRndmAmtValidateReq(IMobileProperties iMobileProperties,String referenceId, String customerNic, String randomAmt) {
//        this.merchantId = iMobileProperties.getJustPayProperties().getProperty("merchantId");
//        this.merchantToken = iMobileProperties.getJustPayProperties().getProperty("merchantToken");
//        this.timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
//        this.referenceId = referenceId;
//        this.customerNic = customerNic;
//        this.randomAmt = randomAmt;
//    }

    public Date timestampToDate(){
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(this.timeStamp);
        }catch (Exception e){
            e.printStackTrace();
            return new Date();
        }
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantToken() {
        return merchantToken;
    }

    public void setMerchantToken(String merchantToken) {
        this.merchantToken = merchantToken;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRandomAmt() {
        return randomAmt;
    }

    public void setRandomAmt(String randomAmt) {
        this.randomAmt = randomAmt;
    }
}
