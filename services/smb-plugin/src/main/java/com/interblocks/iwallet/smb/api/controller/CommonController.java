package com.interblocks.iwallet.smb.api.controller;

import com.interblocks.iwallet.adaptor.rest.admin.IAdminRestClient;
import com.interblocks.iwallet.adaptor.rest.admin.IAdminRestClientV2;
import com.interblocks.iwallet.adaptor.rest.admin.model.GetUserRes;
import com.interblocks.iwallet.adaptor.rest.admin.model.UserCommonResAdmin;
import com.interblocks.iwallet.oauth.PCIExtract;
import com.interblocks.iwallet.smb.model.dto.NICValidationRequest;
import com.interblocks.iwallet.smb.model.dto.ResponseSMBDefault;
import com.interblocks.iwallet.smb.rest.dto.NicValidateRes;
import com.interblocks.iwallet.smb.services.VishwaService;
import com.interblocks.iwallet.subcomponents.instrument.InstrumentService;
import com.interblocks.iwallet.subcomponents.instrument.db.model.WltDmUsrAcct;
import com.interblocks.iwallet.subcomponents.user.UserService;
import com.interblocks.iwallet.subcomponents.user.db.model.WltDmUsrRegReq;
import com.interblocks.iwallet.subcomponents.user.db.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/common")
@Log4j2
public class CommonController {

    @Autowired
    VishwaService vishwaService;

    @Autowired
    UserService userService;

    @Autowired
    InstrumentService instrumentService;

    @Autowired
    PCIExtract pciExtract;

    @Value("${defaultBankCode}")
    String defaultBankCode;

    @Autowired
    IAdminRestClient iAdminRestClient;

    @Autowired
    IAdminRestClientV2 iAdminRestClientV2;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/validateNic", consumes = "application/json", produces = "application/json")
    public ResponseSMBDefault validateNic(@RequestBody NICValidationRequest validate)  throws NotFoundException {
//        log.debug("--------------getBankList--------------");
        try {
//            UserPrincipal principal = (UserPrincipal) securityContext.getUserPrincipal();

            String mobileWithZero=validate.getMobile().replaceFirst("94","0");
            String nicType="NICN";
            String nic=validate.getNic().toLowerCase();

            if (nic.contains("v")||nic.contains("x")){
                nicType="NIC";
            }

            NicValidateRes user=vishwaService.validateNic(nicType,nic);

            if (user==null||user.getCifId()==null){
                return new ResponseSMBDefault().status(200).entity(null);
            }

            if (mobileWithZero.equals(user.getSmsNumber())){
                return new ResponseSMBDefault().status(200).entity(null);
            }

        }catch (Exception e){
            log.error("Exception",e);
        }
        return new ResponseSMBDefault().status(401).entity(null);
    }

    @PostMapping(value = "/forgotPw/validateNic", consumes = "application/json", produces = "application/json")
    public ResponseSMBDefault validateNicForForgotPW(@RequestBody NICValidationRequest validate)  throws NotFoundException {
//        log.debug("--------------getBankList--------------");
        try {
            HashMap<String,Object> map = new HashMap<>();
            List<WltDmUsrAcct> allInstruments=new ArrayList<>();

            WltDmUsrRegReq regReq=userService.findUserByField("nic",validate.getNic());


            if (regReq!=null){
                map.put("isUserExists",true);

                allInstruments=instrumentService.getAllInstruments(regReq.getUsrId());

                map.put("isInstrumentExists",!allInstruments.isEmpty());
                map.put("userId",regReq.getUsrId());
                map.put("mobile",regReq.getMoblNo());
            }else {
                map.put("isUserExists",false);
            }




            return new ResponseSMBDefault().status(200).entity(map);

        }catch (Exception e){
            log.error("Exception",e);
        }
        return new ResponseSMBDefault().status(401).entity(null);


    }

    @PostMapping(value = "/forgotPw/validateInstrument", consumes = "application/json", produces = "application/json")
    public ResponseSMBDefault validateInstrumentForForgotPW(@RequestBody NICValidationRequest validate)  throws NotFoundException {
//        log.debug("--------------getBankList--------------");
        try {
            boolean isInstrumentVerified=false;
            boolean isEmailValidated=false;
            List<WltDmUsrAcct> allInstruments=new ArrayList<>();
            HashMap<String,Object> map = new HashMap<>();
            System.out.println("-----------" + validate.getUserId()+"---------"+validate.getEmail()+"-------"+defaultBankCode);
            WltDmUsrRegReq regReq=userService.getUserByUserId(validate.getUserId(),defaultBankCode);

            if (!regReq.getEmail().equalsIgnoreCase(validate.getEmail())){
                log.error("email validation failed");
                isEmailValidated=false;
                map.put("isEmailValidated",isEmailValidated);
                return new ResponseSMBDefault().status(401).entity(map);
            }

            isEmailValidated=true;



            allInstruments=instrumentService.getAllInstruments(validate.getUserId());
            if (!allInstruments.isEmpty()){

                if (validate.getCrdNo()==null||validate.getCrdNo().isEmpty()){
                    log.error("card or account details not avialable");
                    map.put("isInstrumentValidated",false);
                    return new ResponseSMBDefault().status(401).entity(map);
                }


                for (WltDmUsrAcct acct:allInstruments){
                    if (!acct.getCrdTyp().equals("3")&&validate.getCrdNo().trim().equals(pciExtract.DecryptString(acct.getCrdNo()))){
                        isInstrumentVerified=true;
                        break;
                    }
                }

            }else {
                isInstrumentVerified=true;
            }


            map.put("isEmailValidated",isEmailValidated);
            map.put("isInstrumentVerified",isInstrumentVerified);



            if (isEmailValidated&&isInstrumentVerified){

                log.info("reseting password for "+validate.getUserId() );
                WltDmUsrRegReq wltDmUsrRegReq =userService.getUserByUserId(validate.getUserId(),defaultBankCode);

                GetUserRes adminUser = iAdminRestClient.getWalletUser(wltDmUsrRegReq.getUsrId(), defaultBankCode);

                log.info("reset user object", adminUser);
                String otpMode = "SMS";
//                if (adminUser.getUserDetails().getEmail().isEmpty() || adminUser.getUserDetails().getEmail().equals("")|| adminUser.getUserDetails().getEmail().equalsIgnoreCase("NA")) {
//                    otpMode = "SMS";
//                } else if (adminUser.getUserDetails().getMobile().isEmpty()||adminUser.getUserDetails().getMobile().equals("")|| adminUser.getUserDetails().getMobile().equalsIgnoreCase("NA")) {
//                    otpMode = "EMAIL";
//                }
                UserCommonResAdmin res=iAdminRestClientV2.resetPassword(validate.getUserId(), otpMode);
                if (res.getStaus().equals("00")){
                    wltDmUsrRegReq.setForcePassword(true);
                    userRepository.save(wltDmUsrRegReq);
                    log.info("reset password successful for "+validate.getUserId());
                }else {
                    return new ResponseSMBDefault().status(401).entity(null);
                }


//                map.put("sessionId",res.get);
            }




//            map.put("isUserExist",regReq!=null?true:false);
//            NicValidateRes user=vishwaService.validateNic(validate.getNic());
//            if (validate.getMobile().equals(user.getSmsNumber())){
            return new ResponseSMBDefault().status(200).entity(map);
//            }

        }catch (Exception e){
            log.error("Exception",e);
        }
        return new ResponseSMBDefault().status(401).entity(null);


    }


}


