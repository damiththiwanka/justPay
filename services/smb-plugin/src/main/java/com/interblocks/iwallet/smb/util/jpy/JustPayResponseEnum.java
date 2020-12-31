package com.interblocks.iwallet.smb.util.jpy;

public enum JustPayResponseEnum {
    SUCCESSFUL("0000","Successful",false,false),
    INVALID_MERCHANT_CREDENTIALS("1001","Invalid Merchant Credentials",false,false),
    INVALID_NIC_FORMAT("1002","Invalid NIC format",true,false),
    INVALID_ACCOUNT("1003","Invalid Account",true,false),
    ONLY_OPERATIVE_ACCOUNTS_ARE_ALLOWED("1004","Only operative accounts are allowed",true,false),
    INACTIVE_ACCOUNT("1005","Inactive Account",true,false),
    FOREIGN_CURRENCY_ACCOUNTS_ARE_NOT_ALLOWED("1006","Foreign Currency accounts are not allowed",true,false),
    FUND_TRANSFERS_ARE_RESTRICTED_FOR_THIS_ACCOUNT("1007","Fund transfers are restricted for this account",true,false),
    JOINT_ACCOUNTS_ARE_NOT_ALLOWED("1008","Joint accounts are not allowed",true,false),
    ACCOUNT_IS_ALREADY_REGISTERED("1009","Account is already registered",true,false),
    MAXIMUM_NUMBER_OF_DEVICES_IS_REACHED("1010","Maximum number of devices is reached",true,false),
    INVALID_DIGITAL_SIGNATURE("1011","Invalid Digital Signature",false,false),
    REGISTRATION_REQUEST_NOT_FOUND("1012","Registration request not found",false,false),
    INVALID_AMOUNT_ENTERED("1013","Invalid amount entered",true,false),
    REGISTRATION_REQUEST_WITH_CONSENT_NOT_FOUND("1014","Registration request with consent not found",true,false),
    SUB_MERCHANT_IS_NOT_VALID("1015","Sub Merchant is not valid",false,false),
    CUSTOMER_IS_NOT_VALID("1016","Customer is not valid",false,false),
    CUSTOMER_IS_NOT_REGISTERED_FOR_THIS_PAYMENT("1017","Customer is not registered for this payment",false,false),
    LIMIT_VALIDATION_FAILED("1018","Limit validation failed",true,false),
    ERROR_IN_MERCHANT_VALIDATION("2001","Error in Merchant Validation",false,false),
    DUPLICATE_REGISTRATION_REQUEST("2002","Duplicate registration request",true,false),
    ERROR_IN_CUSTOMER_REGISTRATION("2003","Error in Customer registration",false,false),
    ERROR_IN_CONSENT_ADDITION("2004","Error in consent addition",false,false),
    ERROR_IN_RANDOM_TRANSACTION("2005","Error in Random transaction",false,false),
    ERROR_IN_RANDOM_AMOUNT_VALIDATION("2006","Error in Random amount validation",true,false),
    TRANSACTION_ALREADY_PROCESSED("2007","Transaction already processed",true,false),
    ERROR_IN_PAYMENT_REQUEST("2008","Error in Payment request",false,false),
    ERROR_IN_STATUS_INQUIRY("2009","Error in Status Inquiry",false,false),
    ERROR_IN_CUSTOMER_REMOVAL("2010","Error in Customer Removal",false,false),
    RANDOM_TRANSACTION_FAILED("3001","Random transaction failed",false,false),
    TRANSACTION_FAILED("3002","Transaction failed",false,false),
    CHALLENGE_ISSUANCE_FAILED("3003","Challenge issuance failed",false,false),


    ACCOUNT_SERVICE_FAILED("99","Account service failed",true,false),
    INTERNAL_SERVICE_ERROR("98","Internal service error",true,false),
    NOT_IN_A_VALID_STATE("90","Not in a valid state",true,false);

    private String code;
    private String desc;
    private boolean sendToCustomer;
    private boolean deleteJPentry;

    JustPayResponseEnum(String code,String desc,boolean sendToCustomer,boolean deleteJPentry){
        this.code = code;
        this.desc = desc;
        this.sendToCustomer = sendToCustomer;
        this.deleteJPentry = deleteJPentry;
    }


    public static JustPayResponseEnum getByValue(String code) {
        for (JustPayResponseEnum e : values()) {
            if (e.code.equals(code)) return e;
        }
        throw new IllegalArgumentException();
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        if (this.sendToCustomer){
            return desc;
        }else{
            return "Exception in Account service";
        }

    }

    public boolean isSendToCustomer() {
        return sendToCustomer;
    }

    public boolean isDeleteJPentry() {
        return deleteJPentry;
    }
}
