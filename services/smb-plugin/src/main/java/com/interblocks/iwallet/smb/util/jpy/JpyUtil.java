package com.interblocks.iwallet.smb.util.jpy;
//
//import io.swagger.model.PostAccount;
//import io.swagger.model.ResponseDefaultError;
//import io.swagger.model.ResponseDefaultSuccess;

import com.interblocks.iwallet.smb.rest.SMBRestClient;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import javax.ws.rs.core.Response;

@Component
@Log4j2
public class JpyUtil {


    @Autowired
    SMBRestClient restClient;


//    @Autowired
//    WltJpyAcctRepository wltJpyAcctRepository;

    String operation_register="REGISTER";
    String operation_giveConsent="GIVE_CONSENT";
    String operation_randomAmtVerify="RANDOM_AMOUNT_VERIFY";
    String operation_transaction="TRANSACTION";
    String operation_delete="DELETE";

    String status_validation_error_code="90";
    String internal_error_code="98";
    String exception_error_code="99";

    @Value("${justpay.url}")
    String jpyUrl;

    @Value("${justpay.bankListConfFile}")
    String jpyBankListConfFile;




    public List<JPBank> getBankList(){

        try {

            System.out.println("------"+jpyBankListConfFile);

            log.debug("getBankList initiated");

            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<XMLConfiguration> builder =
                    new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class)
                            .configure(params.xml()
                                    .setFileName(jpyBankListConfFile)
                                    .setValidating(false));


            log.debug("file retrieved");
            XMLConfiguration config = builder.getConfiguration();
            log.debug("configurations loaded");

            //String stage = config.getString("BANK_LIST[@stage]");
            List<String> banks = config.getList(String.class, "bankList.bank");

            log.debug(banks);

            List<JPBank> bankList=new ArrayList<>();

            int i=0;
            for (String bank:banks){
                bankList.add(new JPBank(config.getString("bankList.bank("+i+")[@code]"),bank,config.getString("bankList.bank("+i+")[@length]")));
                i++;
            }

            return bankList;

        }catch (Exception e){
            log.error("Exception",e);
        }
        return Collections.emptyList();
    }

    public boolean hasPrimaryAccount(String userId,String deviceId){
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        return jpInstrumentBo.findPrimaryAccByUserIdAndDeviceId(userId,deviceId)!=null;
        return true;
    }

//    public WltDmJpyAcct getJpyAcct(long jpid){
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        return jpInstrumentBo.findByPk(jpid);
//    }
//
//    public boolean validateState(long jpid,String operation){
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        WltDmJpyAcct wltDmJpyAcct = jpInstrumentBo.findByPk(jpid);
//        return validateState(wltDmJpyAcct,operation);
//    }

//    public boolean validateState(WltDmJpyAcct wltDmJpyAcct,String operation){
//
//        log.debug("WltDmJpyAcct is in "+JustPayStatusEnum.getByValue(wltDmJpyAcct.getJpStatus()).name()+" Status");

//        JustPayStatusRequest justPayStatusRequest=new JustPayStatusRequest(properties,wltDmJpyAcct.getReferenceId(),wltDmJpyAcct.getCustomerNic());
//        JustPayResponse statusResp = restClient.justPayStatusInquery(justPayStatusRequest);
//
//
//        if (statusResp==null){
//            logger.error("statusResp is null");
//            return false;
//        }
//
//
//        switch(statusResp.getStatus()) {
//            case "U":
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.UNDEFINED.getValue());
//                wltDmJpyAcct.setStatus((short)99);
//                break;
//            case "E":
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.REGISTERED.getValue());
//                wltDmJpyAcct.setStatus((short)5);
//                break;
//            case "C":
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.REGISTERED.getValue());
//                wltDmJpyAcct.setStatus((short)5);
//                break;
//            case "R":
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.CONSENT_ADDED.getValue());
//                wltDmJpyAcct.setStatus((short)5);
//                break;
//            case "V":
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.RANDOM_VERIFIED.getValue());
//                wltDmJpyAcct.setStatus((short)1);
//                break;
//            case "D":
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.DELETED.getValue());
//                wltDmJpyAcct.setStatus((short)99);
//                break;
//        }
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        jpInstrumentBo.update(wltDmJpyAcct);
//
//
//        logger.debug("statusResp : "+statusResp.getStatus());
//
//        if (operation.equalsIgnoreCase(operation_register)){
//            if (statusResp.getStatus().equals("D")) {
//                logger.debug("Deleted JP Account found");
//                return true;
//            }else if (statusResp.getStatus().equals("U")){
//                logger.debug("No JP Account found. Status : "+statusResp.getStatus());
//                return true;
//            }else{
//                logger.error("WltDmJpyAcct is in "+JustPayStatusEnum.getByValue(wltDmJpyAcct.getJpStatus()).name()+" Status");
//                return false;
//            }
//
//        }else if (operation.equalsIgnoreCase(operation_giveConsent)){
//            if (statusResp.getStatus().equals("C")){
//                logger.debug("Entered JP Account found. Status : "+statusResp.getStatus());
//                return true;
//            }else if (statusResp.getStatus().equals("D")) {
//                logger.error("Deleted JP Account found");
//                return false;
//            }else if (statusResp.getStatus().equals("U")){
//                logger.error("No JP Account found. Status : "+statusResp.getStatus());
//                return false;
//            }else{
//                logger.error("WltDmJpyAcct is in "+statusResp.getStatus()+" Status");
//                return false;
//            }
//
//        }else if (operation.equalsIgnoreCase(operation_randomAmtVerify)){
//            if (statusResp.getStatus().equals("R")){
//                logger.debug("Entered JP Account found. Status : "+wltDmJpyAcct.getJpStatus());
//                return true;
//            }else if (statusResp.getStatus().equals("D")) {
//                logger.error("Deleted JP Account found");
//                return false;
//            }else if (statusResp.getStatus().equals("U")){
//                logger.error("No JP Account found. Status : "+wltDmJpyAcct.getJpStatus());
//                return false;
//            }else{
//                logger.error("WltDmJpyAcct is in "+statusResp.getStatus()+" Status");
//                return false;
//            }
//
//        }else if (operation.equalsIgnoreCase(operation_transaction)){
//            if (statusResp.getStatus().equals("V")){
//                logger.debug("Entered JP Account found. Status : "+wltDmJpyAcct.getJpStatus());
//                return true;
//            }if (statusResp.getStatus().equals("D")) {
//                logger.debug("Deleted JP Account found");
//                return false;
//            }else if (statusResp.getStatus().equals("U")){
//                logger.debug("No JP Account found. Status : "+wltDmJpyAcct.getJpStatus());
//                return false;
//            }else{
//                logger.error("WltDmJpyAcct is in "+statusResp.getStatus()+" Status");
//                return false;
//            }
//        }else if (operation.equalsIgnoreCase(operation_delete)){
//            if (statusResp.getStatus().equals("D")) {
//                logger.debug("Deleted JP Account found");
//                return true;
//            }else if (statusResp.getStatus().equals("U")){
//                logger.debug("No JP Account found. Status : "+statusResp.getStatus());
//                return false;
//            }else{
//                logger.debug("WltDmJpyAcct is in "+statusResp.getStatus()+" Status");
//                return true;
//            }
//        }
//
//        return false;
//    }

    public String justPayDelete(String userId,String accountNo){

        String result=exception_error_code;
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//
//        List<WltDmJpyAcct> jpyAccts = jpInstrumentBo.findByUserIdandAccountNumber(userId,accountNo,properties.GetDefaultBankCode());
//
//        if (jpyAccts.isEmpty()){
//            result="0000";
//        }else {
//            for (WltDmJpyAcct jpyAcct:jpyAccts){
//                String responseCode=justPayDelete(jpyAcct,jpyAcct.getStatus()==1);
//                if (!responseCode.equals("0000")){
//                    result=responseCode;
//                    break;
//                }else {
//                    result="0000";
//                }
//            }
//        }

        return result;
    }

    public String justPayDelete(long id,boolean isActive){

//        logger.debug("finding WltDmJpyAcct from id: "+id);
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//
//        WltDmJpyAcct wltDmJpyAcct = jpInstrumentBo.findByPk(id);
//
//        if (wltDmJpyAcct!=null){
//            return justPayDelete(wltDmJpyAcct,isActive);
//        }
//
//        logger.error("cannot find WltDmJpyAcct from id: "+id);
        return exception_error_code;
    }

//    public String justPayDelete(WltDmJpyAcct wltDmJpyAcct,boolean isActive){

//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        JustPayResponse deleteResp=null;
//        try {
//
//            if (isActive){
//                if(!validateState(wltDmJpyAcct,operation_delete)){
//                    logger.error("validation for delete justpay account has been failed. Acc: "+wltDmJpyAcct.getAccountNumber());
//                    return status_validation_error_code;
//                }
//
//                logger.debug("WltDmJpyAcct is in "+JustPayStatusEnum.getByValue(wltDmJpyAcct.getJpStatus()).name()+" Status");
//
//                JustPayDeleteUserRequest deleteUserRequest=new JustPayDeleteUserRequest(properties,wltDmJpyAcct.getReferenceId(),wltDmJpyAcct.getCustomerToken(),wltDmJpyAcct.getAccountReference());
//                deleteResp = restClient.justPayDeleteUser(deleteUserRequest);
//
//
//                if (deleteResp==null){
//                    logger.error("deleteResp is null");
//                    return exception_error_code;
//                }
//
//                wltDmJpyAcct.setModifiedDate(new Date());
//                wltDmJpyAcct.setJpyRespCode(deleteResp.getResponseCode());
//                wltDmJpyAcct.setJpyRespDesc(deleteResp.getResponseDesc());
//
//                if (!deleteResp.getResponseCode().equals("0000")){
//                    jpInstrumentBo.update(wltDmJpyAcct);
//                    logger.error("deleteResp.getResponseCode : "+deleteResp.getResponseCode());
//                    return deleteResp.getResponseCode();
//                }
//
//            }
//
//            wltDmJpyAcct.setModifiedDate(new Date());
//            wltDmJpyAcct.setStatus((short)InstrumentStatus.DELETED.getValue());
//            wltDmJpyAcct.setJpStatus(JustPayStatusEnum.DELETED.getValue());
//            jpInstrumentBo.update(wltDmJpyAcct);
//
//            return deleteResp.getResponseCode();
//        }catch (Exception e){
//            logger.error("Exception : ",e);
//            return exception_error_code;
//        }

//    }

//    public String justPayRegisterSS(WltDmUserRegReq regReq,String accNo,String instrunemtBankCode,String deviceId,String platform,boolean hasCertificate){
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        WltDmJpyAcct wltDmJpyAcct=null;
//
//        try {
//
//            JustPayRegistrationRequest request=new JustPayRegistrationRequest(properties,regReq);
//
//            request.setAccountNumber(accNo);
//            request.setBankCode(instrunemtBankCode);
//            request.setDeviceID(deviceId);
//            request.setPlatform(platform);
//            request.setReferenceId(new Date().getTime()+""+String.valueOf(regReq.getUserRegId()).substring(2));
//
//
//            List<WltDmJpyAcct> jpyAcct =jpInstrumentBo.findByDeviceUserIdAndNIC(deviceId,regReq.getUserId(),regReq.getNic(),properties.GetDefaultBankCode());
//
//            WltDmJpyAcct selectedJpyAcc = jpyAcct.stream()
//                    .filter(acc -> accNo.equals(acc.getAccountNumber()))
//                    .findAny()
//                    .orElse(null);
//
//            if (selectedJpyAcc!=null){
//                if (!validateState(selectedJpyAcc,"REGISTER")){
//                    return null;
//                }
//                wltDmJpyAcct=selectedJpyAcc;
//
//            }else {
//                wltDmJpyAcct=new WltDmJpyAcct();
//
//                wltDmJpyAcct.setUserId(regReq.getUserId());
//                wltDmJpyAcct.setCustomerEmail(request.getCustomerEmail());
//                wltDmJpyAcct.setCustomerName(request.getCustomerName());
//                wltDmJpyAcct.setCustomerNic(request.getCustomerNic());
//                wltDmJpyAcct.setCustomerMobile(request.getMobileNumber());
//                wltDmJpyAcct.setAccountNumber(request.getAccountNumber());
//                wltDmJpyAcct.setDeviceId(request.getDeviceID());
//                wltDmJpyAcct.setPlatform(request.getPlatform());
//                wltDmJpyAcct.setInstrumentBankCode(request.getBankCode());
//                wltDmJpyAcct.setInstrumentBranchCode(request.getBranchCode());
//                wltDmJpyAcct.setReferenceId(request.getReferenceId());
//                wltDmJpyAcct.setBankCode(properties.GetDefaultBankCode());
//                wltDmJpyAcct.setAddedDate(new Date());
//                wltDmJpyAcct.setModifiedDate(new Date());
//                wltDmJpyAcct.setReqDate(request.timestampToDate());
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.ADDED.getValue());
//                wltDmJpyAcct.setStatus((short)1);
//
//                if (jpInstrumentBo.save(wltDmJpyAcct) == 0) {
//                    logger.error("JP account saving unsuccessful.");
//                    return null;
//                }
//            }
//
//            if (hasCertificate){
//                    //2nd account adding
//                    request.setTypeCode("02");
//            }else {
//                if (jpyAcct.isEmpty()){
//                    //1st account adding with registration
//                    request.setTypeCode("01");
//                }else {
//                    //reissuance for registered user
//                    request.setTypeCode("03");
//                }
//            }
//
//
//            JustPayResponse jpyResponse=restClient.justPayRegister(request);
//
//            if (jpyResponse==null){
//                logger.error("jpyResponse ResponseCode null");
//                return null;
//            }
//
//
//            wltDmJpyAcct.setChallenge(jpyAcct.isEmpty()?jpyResponse.getChallenge():jpyAcct.get(0).getChallenge());
//            wltDmJpyAcct.setJpyRespCode(jpyResponse.getResponseCode());
//            wltDmJpyAcct.setJpyRespDesc(jpyResponse.getResponseDesc());
//            wltDmJpyAcct.setResDate(jpyResponse.timestampToDate());
//            wltDmJpyAcct.setLcComment(jpyResponse.getLcComment());
//            wltDmJpyAcct.setModifiedDate(new Date());
//
//            if (!jpyResponse.getResponseCode().equals("0000")){
//                logger.error("jpyResponse.getResponseCode : "+jpyResponse.getResponseCode());
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.REGISTRATION_FAILED.getValue());
//                jpInstrumentBo.update(wltDmJpyAcct);
//                return null;
//            }
//
//            wltDmJpyAcct.setJpStatus(JustPayStatusEnum.REGISTERED.getValue());
//            jpInstrumentBo.update(wltDmJpyAcct);
//            return true;
//
//
//        }catch (Exception e){
//            logger.error("Exception in justpay util");
//            logger.error("Exception",e);
//        }
//        return null;
//
//    }


//    public Response justPayRegister(WltDmUserRegReq regReq, String accNo, PostAccount account){
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//
//        boolean hasPrimary=hasPrimaryAccount(regReq.getUserId(),account.getDeviceId());
//
//        try {
//
//            JustPayRegistrationRequest request=new JustPayRegistrationRequest(properties,regReq,accNo,account.getAccountBankCode(),account.getDeviceId(),account.getPlatform(),hasPrimary?"02":"01");
//
//            JustPayResponse jpyResponse=restClient.justPayRegister(request);
//
//            if (jpyResponse==null){
//                logger.error("jpyResponse ResponseCode null");
//                return Response.status(401).entity(
//                        new ResponseDefaultError().defaultMsg(JustPayResponseEnum.getByValue(exception_error_code).getDesc())).build();
//            }
//
//            if (!jpyResponse.getResponseCode().equals("0000")){
//                logger.error("jpyResponse.getResponseCode : "+jpyResponse.getResponseCode());
//                return Response.status(401).entity(new ResponseDefaultError().defaultMsg(JustPayResponseEnum.getByValue(jpyResponse.getResponseCode()).getDesc())).build();
//            }
//
//            WltDmJpyAcct wltDmJpyAcct=new WltDmJpyAcct();
//
//            wltDmJpyAcct.setUserId(regReq.getUserId());
//            wltDmJpyAcct.setCustomerEmail(request.getCustomerEmail());
//            wltDmJpyAcct.setCustomerName(request.getCustomerName());
//            wltDmJpyAcct.setCustomerNic(request.getCustomerNic());
//            wltDmJpyAcct.setCustomerMobile(request.getMobileNumber());
//            wltDmJpyAcct.setAccountNumber(obPCIExtract.EncryptString(request.getAccountNumber()));
//            wltDmJpyAcct.setDeviceId(request.getDeviceID());
//            wltDmJpyAcct.setPlatform(request.getPlatform());
//            wltDmJpyAcct.setInstrumentBankCode(request.getBankCode());
//            wltDmJpyAcct.setInstrumentBranchCode(request.getBranchCode());
//            wltDmJpyAcct.setReferenceId(request.getReferenceId());
//            wltDmJpyAcct.setBankCode(properties.GetDefaultBankCode());
//            wltDmJpyAcct.setAddedDate(new Date());
//            wltDmJpyAcct.setModifiedDate(new Date());
//            wltDmJpyAcct.setReqDate(request.timestampToDate());
//            wltDmJpyAcct.setStatus((short)InstrumentStatus.INACTIVE.getValue());
//            wltDmJpyAcct.setChallenge(jpyResponse.getChallenge());
//            wltDmJpyAcct.setJpyRespCode(jpyResponse.getResponseCode());
//            wltDmJpyAcct.setJpyRespDesc(jpyResponse.getResponseDesc());
//            wltDmJpyAcct.setResDate(jpyResponse.timestampToDate());
//            wltDmJpyAcct.setLcComment(jpyResponse.getLcComment());
//            wltDmJpyAcct.setModifiedDate(new Date());
//            wltDmJpyAcct.setJpStatus(JustPayStatusEnum.REGISTERED.getValue());
//            wltDmJpyAcct.setMaskedAcctNo(accNo);
////            wltDmJpyAcct.setInstStatus(instrumentStatusEnum.getValue());
////            wltDmJpyAcct.setInstId(instrumentId);
//            wltDmJpyAcct.setIsPrimary(hasPrimary?(short)1:(short)0);
//
//            if (jpInstrumentBo.save(wltDmJpyAcct) == 0) {
//                logger.error("JP account saving unsuccessful.");
//                return Response.status(401).entity(new ResponseDefaultError().defaultMsg("Internal service error")).build();
//            }
//
//            return Response.status(200).entity(new ResponseDefaultSuccess().add("challenge",jpyResponse.getChallenge())).build();
//
//
//        }catch (Exception e){
//            logger.error("Exception in justpay util");
//            logger.error("Exception",e);
//            return Response.status(401).entity(new ResponseDefaultError().defaultMsg(JustPayResponseEnum.getByValue(exception_error_code).getDesc())).build();
//        }
//
//    }



//    public String justPayGiveConsent(WltDmJpyAcct wltDmJpyAcct, String signedConsent){
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//
//        try {
//            if(!validateState(wltDmJpyAcct,operation_giveConsent)){
//                logger.error("validation for GIVE_CONSENT justpay account has been failed. Acc: "+wltDmJpyAcct.getMaskedAcctNo());
//                return status_validation_error_code;
//            }
//
//            JustPayConsentRequest justPayRequest=new JustPayConsentRequest(properties,wltDmJpyAcct.getCustomerNic(),wltDmJpyAcct.getReferenceId(),signedConsent);
//
//            JustPayResponse response=restClient.justPayGetConsent(justPayRequest);
//            if (response==null){
//                logger.error("jpyResponse ResponseCode null");
//                return exception_error_code;
//            }
//
//            wltDmJpyAcct.setJpyRespCode(response.getResponseCode());
//            wltDmJpyAcct.setJpyRespDesc(response.getResponseDesc());
//            wltDmJpyAcct.setResDate(response.timestampToDate());
//            wltDmJpyAcct.setModifiedDate(new Date());
//
//            if (!response.getResponseCode().equals("0000")){
//                logger.error("jpyResponse.getResponseCode : "+response.getResponseCode());
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.CONSENT_ADDING_FAILED.getValue());
//                jpInstrumentBo.update(wltDmJpyAcct);
//                return response.getResponseCode();
//            }
//            wltDmJpyAcct.setStatus((short)InstrumentStatus.PENDING_ENROLLMENT.getValue());
//            wltDmJpyAcct.setJpStatus(JustPayStatusEnum.CONSENT_ADDED.getValue());
//            jpInstrumentBo.update(wltDmJpyAcct);
//
//            return response.getResponseCode();
//        }catch (Exception e){
//            logger.error("Exception : ",e);
//            return exception_error_code;
//        }
//    }

//    public String justPayValidateRandomAmt(WltDmJpyAcct wltDmJpyAcct,String randomAmt){
//
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//
//        try {
//            if(!validateState(wltDmJpyAcct,operation_randomAmtVerify)){
//                logger.error("validation for RANDOM_AMT_VERIFY justpay account has been failed. Acc: "+wltDmJpyAcct.getAccountNumber());
//                return status_validation_error_code;
//            }
//
//            JustPayRndmAmtValidateReq justPayRequest=new JustPayRndmAmtValidateReq(properties,wltDmJpyAcct.getReferenceId(),wltDmJpyAcct.getCustomerNic(),randomAmt);
//
//            JustPayResponse response=restClient.justPayVerifyRandomAmt(justPayRequest);
//
//            if (response==null){
//                logger.error("jpyResponse ResponseCode null");
//                return exception_error_code;
//            }
//
//            wltDmJpyAcct.setJpyRespCode(response.getResponseCode());
//            wltDmJpyAcct.setJpyRespDesc(response.getResponseDesc());
//            wltDmJpyAcct.setResDate(response.timestampToDate());
//            wltDmJpyAcct.setModifiedDate(new Date());
//
//            if (!response.getResponseCode().equals("0000")){
//                logger.error("jpyResponse.getResponseCode : "+response.getResponseCode());
//                wltDmJpyAcct.setJpStatus(JustPayStatusEnum.RANDOM_VERIFY_FAILED.getValue());
//                jpInstrumentBo.update(wltDmJpyAcct);
//                return response.getResponseCode();
//            }
//
//            wltDmJpyAcct.setStatus((short)InstrumentStatus.ACTIVE.getValue());
//            wltDmJpyAcct.setCustomerToken(response.getCustomerToken());
//            wltDmJpyAcct.setAccountReference(response.getAccountReference());
//            wltDmJpyAcct.setJpStatus(JustPayStatusEnum.RANDOM_VERIFIED.getValue());
//            jpInstrumentBo.update(wltDmJpyAcct);
//
//            return response.getResponseCode();
//        }catch (Exception e){
//            logger.error("Exception : ",e);
//            return exception_error_code;
//        }
//    }

}
