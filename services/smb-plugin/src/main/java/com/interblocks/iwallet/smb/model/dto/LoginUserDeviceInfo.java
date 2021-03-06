/*
 * Virtual Wallet
 * API for the Product iWallet
 *
 * OpenAPI spec version: 2.10.1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.interblocks.iwallet.smb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

//import io.swagger.annotations.ApiModelProperty;

/**
 * LoginUserDeviceInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-11-27T08:29:42.308Z")
public class LoginUserDeviceInfo {
    @JsonProperty("type")
    private String type = null;

    @JsonProperty("uuID")
    private String uuID = null;

    @JsonProperty("imei")
    private String imei = null;

    @JsonProperty("serialNumber")
    private String serialNumber = null;

    @JsonProperty("androidSignature")
    private String androidSignature = null;

    @JsonProperty("deviceName")
    private String deviceName = null;

    public LoginUserDeviceInfo type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     **/
    @JsonProperty("type")
//    @ApiModelProperty(example = "ios", value = "")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LoginUserDeviceInfo uuID(String uuID) {
        this.uuID = uuID;
        return this;
    }

    /**
     * Get uuID
     *
     * @return uuID
     **/
    @JsonProperty("uuID")
//    @ApiModelProperty(example = "7e6585b1-86db-473c-96e2-27a7ad08db2f", value = "")
    public String getUuID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
    }

    public LoginUserDeviceInfo imei(String imei) {
        this.imei = imei;
        return this;
    }

    /**
     * Get imei
     *
     * @return imei
     **/
    @JsonProperty("imei")
//    @ApiModelProperty(example = "990000862471854", value = "")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public LoginUserDeviceInfo serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    /**
     * Get serialNumber
     *
     * @return serialNumber
     **/
    @JsonProperty("serialNumber")
//    @ApiModelProperty(example = "M8493", value = "")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LoginUserDeviceInfo androidSignature(String androidSignature) {
        this.androidSignature = androidSignature;
        return this;
    }

    /**
     * Get androidSignature
     *
     * @return androidSignature
     **/
    @JsonProperty("androidSignature")
//    @ApiModelProperty(example = "EA:12:5T:6T:XZ:18", value = "")
    public String getAndroidSignature() {
        return androidSignature;
    }

    public void setAndroidSignature(String androidSignature) {
        this.androidSignature = androidSignature;
    }

    public LoginUserDeviceInfo deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    /**
     * Get deviceName
     *
     * @return deviceName
     **/
    @JsonProperty("deviceName")
//    @ApiModelProperty(example = "user's iPhone", value = "")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginUserDeviceInfo loginUserDeviceInfo = (LoginUserDeviceInfo) o;
        return Objects.equals(this.type, loginUserDeviceInfo.type) &&
                Objects.equals(this.uuID, loginUserDeviceInfo.uuID) &&
                Objects.equals(this.imei, loginUserDeviceInfo.imei) &&
                Objects.equals(this.serialNumber, loginUserDeviceInfo.serialNumber) &&
                Objects.equals(this.androidSignature, loginUserDeviceInfo.androidSignature) &&
                Objects.equals(this.deviceName, loginUserDeviceInfo.deviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, uuID, imei, serialNumber, androidSignature, deviceName);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LoginUserDeviceInfo {\n");

        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    uuID: ").append(toIndentedString(uuID)).append("\n");
        sb.append("    imei: ").append(toIndentedString(imei)).append("\n");
        sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
        sb.append("    androidSignature: ").append(toIndentedString(androidSignature)).append("\n");
        sb.append("    deviceName: ").append(toIndentedString(deviceName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

