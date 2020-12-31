package com.interblocks.iwallet.smb.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JustpayRestClient {
//
//    final static Logger logger = LogManager.getLogger(JustpayRestClient.class);
//
//    public JustpayRestClient() {
//    }
//
//
//    @PostConstruct
//    public void init(){
//        logger.debug("@@@ RestClient Loaded");
//    }
//
//    @Autowired
//    RestTemplate restTemplate;
//
////    @Autowired
////    CardDetailExtract cardDetailExtract;
//
//    @Value("justpay.url")
//    String jpyUrl;
//
//
//
//    public JustPayResponse justPayRegister(JustPayRegistrationRequest registrationRequest){
//
//        JustPayResponse res = null;
//
//        logger.debug("Method begin - justPayRegister");
//
//        try {
//            Assert.notNull(registrationRequest, "registrationRequest is null");
//
//
//            Assert.notNull(jpyUrl, "getProperty(url) is null");
//
//            logger.debug("Balance API URL " + jpyUrl);
//
//            jpyUrl+="/RegisterDetails";
//
//            logger.debug("Request object : " + JsonPrinter.createPrettyJsonString(registrationRequest));
//
//            HttpEntity<JustPayRegistrationRequest> request = new HttpEntity<>(registrationRequest);
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
