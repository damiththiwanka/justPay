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

import javax.validation.constraints.NotNull;
import java.util.Objects;

//import io.swagger.annotations.ApiModelProperty;

/**
 * ResponseLoginUser
 */
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2018-11-27T08:29:42.308Z")
public class ResponseLoginUser {
    @JsonProperty("token")
    private String token = null;

    @JsonProperty("user")
    private ModelUser user = null;

    @JsonProperty("sessionID")
    private String sessionID = null;

    @JsonProperty("newDevice")
    private Boolean newDevice = null;

    public ResponseLoginUser token(String token) {
        this.token = token;
        return this;
    }

    /**
     * Get token
     *
     * @return token
     **/
    @JsonProperty("token")
//    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResponseLoginUser user(ModelUser user) {
        this.user = user;
        return this;
    }

    /**
     * Get user
     *
     * @return user
     **/
    @JsonProperty("user")
//    @ApiModelProperty(required = true, value = "")
    @NotNull
    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public ResponseLoginUser sessionID(String sessionID) {
        this.sessionID = sessionID;
        return this;
    }

    /**
     * Get sessionID
     *
     * @return sessionID
     **/
    @JsonProperty("sessionID")
//    @ApiModelProperty(example = "SID:ANON:j6oAOxCWZh/CD723LGeXlf-01:034", value = "")
    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public ResponseLoginUser newDevice(Boolean newDevice) {
        this.newDevice = newDevice;
        return this;
    }

    /**
     * Get newDevice
     *
     * @return newDevice
     **/
    @JsonProperty("newDevice")
//    @ApiModelProperty(example = "false", value = "")
    public Boolean isNewDevice() {
        return newDevice;
    }

    public void setNewDevice(Boolean newDevice) {
        this.newDevice = newDevice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseLoginUser responseLoginUser = (ResponseLoginUser) o;
        return Objects.equals(this.token, responseLoginUser.token) &&
                Objects.equals(this.user, responseLoginUser.user) &&
                Objects.equals(this.sessionID, responseLoginUser.sessionID) &&
                Objects.equals(this.newDevice, responseLoginUser.newDevice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, user, sessionID, newDevice);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseLoginUser {\n");

        sb.append("    token: ").append(toIndentedString(token)).append("\n");
        sb.append("    user: ").append(toIndentedString(user)).append("\n");
        sb.append("    sessionID: ").append(toIndentedString(sessionID)).append("\n");
        sb.append("    newDevice: ").append(toIndentedString(newDevice)).append("\n");
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
