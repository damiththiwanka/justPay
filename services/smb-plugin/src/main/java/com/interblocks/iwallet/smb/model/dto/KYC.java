package com.interblocks.iwallet.smb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

//import io.swagger.annotations.ApiModelProperty;

/**
 * KYC
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T07:26:53.756Z")

public class KYC {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("uploaded")
    private Boolean uploaded = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("kycData")
    private String kycData = null;

    @JsonProperty("kycMeta")
    private String kycMeta = null;

    @JsonProperty("kycType")
    private String kycType = null;

    @JsonProperty("comment")
    private String comment = null;

    public KYC id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
//    @ApiModelProperty(value = "")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KYC uploaded(Boolean uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    /**
     * Get uploaded
     *
     * @return uploaded
     **/
//    @ApiModelProperty(value = "")


    public Boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public KYC status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
//    @ApiModelProperty(value = "")


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public KYC kycData(String kycData) {
        this.kycData = kycData;
        return this;
    }

    /**
     * Get kycData
     *
     * @return kycData
     **/
//    @ApiModelProperty(value = "")


    public String getKycData() {
        return kycData;
    }

    public void setKycData(String kycData) {
        this.kycData = kycData;
    }

    public KYC kycMeta(String kycMeta) {
        this.kycMeta = kycMeta;
        return this;
    }

    /**
     * Get kycMeta
     *
     * @return kycMeta
     **/
//    @ApiModelProperty(value = "")


    public String getKycMeta() {
        return kycMeta;
    }

    public void setKycMeta(String kycMeta) {
        this.kycMeta = kycMeta;
    }

    public KYC kycType(String kycType) {
        this.kycType = kycType;
        return this;
    }

    /**
     * Get kycType
     *
     * @return kycType
     **/
//    @ApiModelProperty(value = "")


    public String getKycType() {
        return kycType;
    }

    public void setKycType(String kycType) {
        this.kycType = kycType;
    }

    public KYC comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Get comment
     *
     * @return comment
     **/
//    @ApiModelProperty(value = "")


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KYC KYC = (KYC) o;
        return Objects.equals(this.id, KYC.id) &&
                Objects.equals(this.uploaded, KYC.uploaded) &&
                Objects.equals(this.status, KYC.status) &&
                Objects.equals(this.kycData, KYC.kycData) &&
                Objects.equals(this.kycMeta, KYC.kycMeta) &&
                Objects.equals(this.kycType, KYC.kycType) &&
                Objects.equals(this.comment, KYC.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uploaded, status, kycData, kycMeta, kycType, comment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KYC {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    uploaded: ").append(toIndentedString(uploaded)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    kycData: ").append(toIndentedString(kycData)).append("\n");
        sb.append("    kycMeta: ").append(toIndentedString(kycMeta)).append("\n");
        sb.append("    kycType: ").append(toIndentedString(kycType)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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

