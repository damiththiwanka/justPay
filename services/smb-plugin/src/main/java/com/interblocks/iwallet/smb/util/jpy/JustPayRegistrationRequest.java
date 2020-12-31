package com.interblocks.iwallet.smb.util.jpy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JustPayRegistrationRequest {

    @JsonProperty("MerchantID")
    String merchantId;

    @JsonProperty("MerchantToken")
    String merchantToken;

    @JsonProperty("ReferenceId")
    String referenceId;

    @JsonProperty("TimeStamp")
    String timeStamp;

    @JsonProperty("CustomerName")
    String customerName;

    @JsonProperty("CustomerNic")
    String customerNic;

    @JsonProperty("MobileNumber")
    String mobileNumber;

    @JsonProperty("CustomerEmail")
    String customerEmail;

    @JsonProperty("TypeCode")
    String typeCode;

    @JsonProperty("BankCode")
    String bankCode;

    @JsonProperty("BranchCode")
    String branchCode;

    @JsonProperty("AccountNumber")
    String accountNumber;

    @JsonProperty("JustPayCode")
    String justPayCode;

    @JsonProperty("DeviceID")
    String deviceID;

    @JsonProperty("Platform")
    String platform;


    public JustPayRegistrationRequest() {
    }

//    public JustPayRegistrationRequest(IMobileProperties iMobileProperties, WltDmUserRegReq regReq,String accNo,String bankCode,String deviceId,String platform,String typeCode){
//        this.merchantId = iMobileProperties.getJustPayProperties().getProperty("merchantId");
//        this.merchantToken = iMobileProperties.getJustPayProperties().getProperty("merchantToken");
//        this.timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
//        this.accountNumber = accNo;
//        this.deviceID = deviceId;
//        this.platform = platform;
//        this.customerName = regReq.getName();
//        this.customerNic = regReq.getNic();
//        this.mobileNumber = regReq.getMobileNumber();
//        this.customerEmail = regReq.getEmail();
//        this.branchCode = "001";
//        this.bankCode = bankCode;
//        this.typeCode = typeCode;
//        this.justPayCode = iMobileProperties.getJustPayProperties().getProperty("justPayCode");
//        String regId=String.valueOf(regReq.getUserRegId());
//        this.referenceId=(new Date().getTime()+""+regId.substring(regId.length()-2));
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getJustPayCode() {
        return justPayCode;
    }

    public void setJustPayCode(String justPayCode) {
        this.justPayCode = justPayCode;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
