package com.interblocks.iwallet.smb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ResponseDefault
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-04T13:31:29.729Z")

public class ResponseDefault {
    @JsonProperty("responseCode")
    private String responseCode = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("responseValues")
    @Valid
    private List<String> responseValues = null;

    @JsonProperty("validationValues")
    @Valid
    private List<String> validationValues = null;

    public ResponseDefault responseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    /**
     * Get responseCode
     *
     * @return responseCode
     **/
    @ApiModelProperty(value = "")


    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseDefault description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @ApiModelProperty(value = "")


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResponseDefault responseValues(List<String> responseValues) {
        this.responseValues = responseValues;
        return this;
    }

    public ResponseDefault addResponseValuesItem(String responseValuesItem) {
        if (this.responseValues == null) {
            this.responseValues = new ArrayList<String>();
        }
        this.responseValues.add(responseValuesItem);
        return this;
    }

    /**
     * Get responseValues
     *
     * @return responseValues
     **/
    @ApiModelProperty(value = "")


    public List<String> getResponseValues() {
        return responseValues;
    }

    public void setResponseValues(List<String> responseValues) {
        this.responseValues = responseValues;
    }

    public ResponseDefault validationValues(List<String> validationValues) {
        this.validationValues = validationValues;
        return this;
    }

    public ResponseDefault addValidationValuesItem(String validationValuesItem) {
        if (this.validationValues == null) {
            this.validationValues = new ArrayList<String>();
        }
        this.validationValues.add(validationValuesItem);
        return this;
    }

    /**
     * Get validationValues
     *
     * @return validationValues
     **/
    @ApiModelProperty(value = "")


    public List<String> getValidationValues() {
        return validationValues;
    }

    public void setValidationValues(List<String> validationValues) {
        this.validationValues = validationValues;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseDefault responseDefault = (ResponseDefault) o;
        return Objects.equals(this.responseCode, responseDefault.responseCode) &&
                Objects.equals(this.description, responseDefault.description) &&
                Objects.equals(this.responseValues, responseDefault.responseValues) &&
                Objects.equals(this.validationValues, responseDefault.validationValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, description, responseValues, validationValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseDefault {\n");

        sb.append("    responseCode: ").append(toIndentedString(responseCode)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    responseValues: ").append(toIndentedString(responseValues)).append("\n");
        sb.append("    validationValues: ").append(toIndentedString(validationValues)).append("\n");
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

