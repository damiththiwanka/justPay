package com.interblocks.iwallet.smb.rest;

import com.interblocks.iwallet.adaptor.util.JsonPrinter;
import com.interblocks.iwallet.smb.rest.dto.NicValidateReq;
import com.interblocks.iwallet.smb.rest.dto.NicValidateRes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@Component
@Log4j2
public class SMBRestClient {

//    public SMBRestClient() {
//    }

    @Autowired
    RestTemplate restTemplate;
//
////    @Autowired
////    CardDetailExtract cardDetailExtract;
//
    @Value("${smb.customer.inquiry.url}")
    String validateUrl;

    public NicValidateRes validateNic(String nicType,String nic){

        NicValidateReq nicValidateReq=new NicValidateReq(nicType,nic);
        NicValidateRes res = null;

        log.debug("Method begin - justPayRegister");

        try {

            log.debug("Balance API URL " + validateUrl);

            log.debug("Request object : " + JsonPrinter.createPrettyJsonString(nicValidateReq));

            HttpEntity<NicValidateReq> request = new HttpEntity<>(nicValidateReq);
            res = restTemplate.postForObject(validateUrl, request, NicValidateRes.class);


            log.debug("Response object : " + JsonPrinter.createPrettyJsonString(res));

        } catch (Exception e) {
            log.error("Error Occured " , e);
        }
        return res;


    }
//
//    public JustPayResponse justPayGetConsent(JustPayConsentRequest consentRequest){
//
//        JustPayResponse res = null;
//
//        logger.debug("Method begin - justPayGetConsent");
//
//        try {
//            Assert.notNull(consentRequest, "cardNumber is null");
//
//            Assert.notNull(jpyUrl, "properties.JustPayurl() is null");
//
//            jpyUrl+="/GiveConsent";
//
//            logger.debug("justPayGetConsent API URL " + jpyUrl);
//            logger.debug("Request object : " + JsonPrinter.createPrettyJsonString(consentRequest));
//
//            HttpEntity<JustPayConsentRequest> request = new HttpEntity<>(consentRequest);
//            res = restTemplate.postForObject(jpyUrl, request, JustPayResponse.class);
//
//            Assert.notNull(res, "response is null");
//
//            logger.debug("Response object : " + JsonPrinter.createPrettyJsonString(res));
//
//        } catch (Exception e) {
//            logger.error("Error Occured " , e);
//        }
//        return res;
//
//
//    }
//
//    public JustPayResponse justPayVerifyRandomAmt(JustPayRndmAmtValidateReq rndmAmtValidateReq){
//
//        JustPayResponse res = null;
//
//        logger.debug("Method begin - justPayVerifyRandomAmt");
//
//        try {
//            Assert.notNull(rndmAmtValidateReq, "rndmAmtValidateReq is null");
//            Assert.notNull(jpyUrl, "properties.justPayurl() is null");
//
//            jpyUrl+="/ValidateAmt";
//
//            logger.debug("justPayVerifyRandomAmt API URL " + jpyUrl);
//
//            logger.debug("Request object : " + JsonPrinter.createPrettyJsonString(rndmAmtValidateReq));
//
//            HttpEntity<JustPayRndmAmtValidateReq> request = new HttpEntity<>(rndmAmtValidateReq);
//            res = restTemplate.postForObject(jpyUrl, request, JustPayResponse.class);
//
//            Assert.notNull(res, "response is null");
//
//            logger.debug("Response object : " + JsonPrinter.createPrettyJsonString(res));
//
//        } catch (Exception e) {
//            logger.error("Error Occured " , e);
//        }
//        return res;
//
//
//    }
//
//    public JustPayResponse justPayStatusInquery(JustPayStatusRequest statusRequest){
//
//        JustPayResponse res = null;
//
//        logger.debug("Method begin - justPayStatusInquery");
//
//        try {
//            Assert.notNull(statusRequest, "statusRequest is null");
//
//            Assert.notNull(jpyUrl, "properties.justPayurl() is null");
//
//            logger.debug("Balance API URL " + jpyUrl);
//
//            jpyUrl+="/InquireStatus";
//
//            logger.debug("Request object : " + JsonPrinter.createPrettyJsonString(statusRequest));
//
//            HttpEntity<JustPayStatusRequest> request = new HttpEntity<>(statusRequest);
//            res = restTemplate.postForObject(jpyUrl, request, JustPayResponse.class);
//
//            Assert.notNull(res, "response is null");
//
//            logger.debug("Response object : " + JsonPrinter.createPrettyJsonString(res));
//
//        } catch (Exception e) {
//            logger.error("Error Occured " , e);
//        }
//        return res;
//
//
//    }
//
//    public JustPayResponse justPayDeleteUser(JustPayDeleteUserRequest deleteUserRequest){
//
//        JustPayResponse res = null;
//
//        logger.debug("Method begin - justPayDeleteUser");
//
//        try {
//            Assert.notNull(deleteUserRequest, "statusRequest is null");
//
//            Assert.notNull(jpyUrl, "properties.justPayurl() is null");
//
//            logger.debug("Balance API URL " + jpyUrl);
//
//            jpyUrl+="/RemoveUser";
//
//            logger.debug("Request object : " + JsonPrinter.createPrettyJsonString(deleteUserRequest));
//
//            HttpEntity<JustPayDeleteUserRequest> request = new HttpEntity<>(deleteUserRequest);
//            res = restTemplate.postForObject(jpyUrl, request, JustPayResponse.class);
//
//            Assert.notNull(res, "response is null");
//
//            logger.debug("Response object : " + JsonPrinter.createPrettyJsonString(res));
//
//        } catch (Exception e) {
//            logger.error("Error Occured " , e);
//        }
//        return res;
//
//
//    }



}
