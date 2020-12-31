package com.interblocks.iwallet.smb.services;

import com.interblocks.iwallet.smb.util.jpy.JPBank;
import com.interblocks.iwallet.smb.util.jpy.JpyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;
import java.util.List;


@Component
public class JpyServiceImpl implements JpyService{

    @Autowired
    JpyUtil jpyUtil;

    @Override
    public List<JPBank> getBankList() {
        return jpyUtil.getBankList();
    }

//    @Override
//    public Response registerAccount(PostAccount account) {
//        userId = userPrincipal.getId();
//        bankCode = properties.GetDefaultBankCode();
//        JPInstrumentBo jpInstrumentBo = new JPInstrumentBoImpl(jpInstrumentDao);
//        InstrumentBo instrumentBo = new InstrumentBoImpl(instrumentDao);
//        UserBo userBo = new UserBoImpl(userDao);
//        long newActyLogLinkId = actiutil.getNextActivityLinkId();
//
//
//        try {
//
//            actiutil.writeActivity(ActivityLinkType.USER_REG_REQ, userId, ActivityType.USER_REQUEST_USERATTEMPTSTODOANACCOUNTENROLLMENT_USERID_0,
//                    "attempting account enrollement", null, properties.GetDefaultBankCode(),newActyLogLinkId);
//
//
//            WltDmUserRegReq userRegistered = userBo.findByPk(userId, properties.GetDefaultBankCode());
//
//            if (null == userRegistered.getUserId() || userRegistered.getUserId().isEmpty()) {
//                logger.error("Requested user not found");
//
//                HashMap<Object, Object> activityMap = new HashMap<>();
//                activityMap.put(ParamName.FAIL_REASON, FailReason.USER_ID_IS_INVALID);
//                actiutil.writeActivity(ActivityLinkType.USER_REG_REQ, userId, ActivityType.USER_REQUEST_ACCOUNTENROLLEMENTUNSUCCESSFULL_USERID_0_FAILREASON_1,
//                        "attempting an account enrollment failure ", activityMap, properties.GetDefaultBankCode(),newActyLogLinkId);
//
//                return javax.ws.rs.core.Response.status(401).entity(new InlineResponse401().errorCode("ER003")).build();
//            }
//
//            logger.info("Rule Engine Allowed");
//
//            Pattern[] xssPatterns = walletUtill.getXSSPatterns();
//
//            for (Pattern scriptPattern : xssPatterns) {
//                account.setAccountName(scriptPattern.matcher(account.getAccountName()).replaceAll(""));
//            }
//
//            for (Pattern scriptPattern : xssPatterns) {
//                account.setAlias(scriptPattern.matcher(account.getAlias()).replaceAll(""));
//            }
//
//            String trimmedAccNumber = account.getAccountNumber().replaceAll(" ", "");
//
//            logger.info("Trimmed Account No:" + trimmedAccNumber);
//
//
//            if (Pattern.compile("[^0-9]+").matcher(trimmedAccNumber).find()) {
//                logger.error("Invalid Account Number");
//
//                HashMap<Object, Object> activityMap = new HashMap<>();
//                activityMap.put(ParamName.FAIL_REASON, FailReason.INVALID_ACCOUNT_FOR_THIS_OPERATION);
//                actiutil.writeActivity(ActivityLinkType.USER_REG_REQ, userId, ActivityType.USER_REQUEST_ACCOUNTENROLLEMENTUNSUCCESSFULL_USERID_0_FAILREASON_1,
//                        "attempting an account enrollment failure ", activityMap, properties.GetDefaultBankCode(),newActyLogLinkId);
//
//                return javax.ws.rs.core.Response.status(401).entity(new InlineResponse401().errorCode("ER009")).build();
//            }
//
//
//            String encAccNo=obPCIExtract.EncryptString(trimmedAccNumber);
//
//            WltDmUserAccounts wltDmUserAccounts = instrumentBo.GetInstrumentByCardNoAndBankCode(bankCode, encAccNo);
//
//
//            if (wltDmUserAccounts!=null&&!wltDmUserAccounts.getUserId().equalsIgnoreCase(userId)){
//                logger.error("user trying to activate justpay for another person's account number");
//                return javax.ws.rs.core.Response.status(401).entity(new ResponseDefaultError().defaultMsg("Account already registered to another user")).build();
//            }
//
//            List<WltDmJpyAcct> wltDmJpyAcctList=jpInstrumentBo.findAccRegisteredToOthers(userId,account.getDeviceId(),encAccNo,bankCode);
//
//            if (!wltDmJpyAcctList.isEmpty()){
//                logger.error("Account already processed for JustPay");
//                return javax.ws.rs.core.Response.status(401).entity(new ResponseDefaultError().defaultMsg("Account already processed for JustPay")).build();
//            }
//
//
//
//
////            long instrumentId=0;
//
//
////            JustPayInstrumentStatusEnum justPayInstrumentStatusEnum=null;
//
////            if (userRegistered.getStatus()=="1"){
////                if (account.getStatus().equalsIgnoreCase("active")){
////                    justPayInstrumentStatusEnum=JustPayInstrumentStatusEnum.REGISTERED_ACTIVE;
////                    instrumentId=Long.parseLong(account.getId());
////                }else if (account.getStatus().equalsIgnoreCase("pending")){
////                    justPayInstrumentStatusEnum=JustPayInstrumentStatusEnum.REGISTERED_PENDING;
////                    instrumentId=Long.parseLong(account.getId());
////                }else if (account.getStatus().equalsIgnoreCase("new")){
////                    justPayInstrumentStatusEnum=JustPayInstrumentStatusEnum.REGISTERED_NEW;
////                }
////            }else {
////
////                if (account.getStatus().equalsIgnoreCase("pending")){
////                    instrumentId=Long.parseLong(account.getId());
////                    justPayInstrumentStatusEnum=JustPayInstrumentStatusEnum.UNREGISTERED_PENDING;
////                }else if (account.getStatus().equalsIgnoreCase("new")){
////                    justPayInstrumentStatusEnum=JustPayInstrumentStatusEnum.UNREGISTERED_NEW;
////                }
////            }
//
//
//            logger.debug("justpay activation for :" + trimmedAccNumber);
//
//            return jpyUtil.justPayRegister(userRegistered,trimmedAccNumber,account);
//        return null;
//    }
}
