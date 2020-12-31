package com.interblocks.iwallet.smb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class VishwaLoginResponse {

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("mobileNo")
    private String mobileNo = null;

    @JsonProperty("nic")
    private String nic = null;

    @JsonProperty("passport")
    private String passport = null;


    public VishwaLoginResponse userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @JsonProperty("userName")
//    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public VishwaLoginResponse mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @JsonProperty("mobileNo")
//    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public VishwaLoginResponse nic(String nic) {
        this.nic = nic;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @JsonProperty("nic")
//    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public VishwaLoginResponse passport(String passport) {
        this.passport = passport;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @JsonProperty("passport")
//    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }


}
