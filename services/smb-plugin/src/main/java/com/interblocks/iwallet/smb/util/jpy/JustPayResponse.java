package com.interblocks.iwallet.smb.util.jpy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JustPayResponse {

    @JsonProperty("TimeStamp")
    String timeStamp;

    @JsonProperty("ReferenceId")
    String referenceId;

    @JsonProperty("ResponseCode")
    String responseCode;

    @JsonProperty("ResponseDesc")
    String responseDesc;

    @JsonProperty("Challenge")
    String challenge;

    @JsonProperty("LCComment")
    String lcComment;

    @JsonProperty("CustomerToken")
    String customerToken;

    @JsonProperty("AccountReference")
    String accountReference;

    @JsonProperty("Status")
    String status;


    public Date timestampToDate(){
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(this.timeStamp);
        }catch (Exception e){
            e.printStackTrace();
            return new Date();
        }

    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getLcComment() {
        return lcComment;
    }

    public void setLcComment(String lcComment) {
        this.lcComment = lcComment;
    }

    public String getCustomerToken() {
        return customerToken;
    }

    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
