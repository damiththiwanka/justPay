package com.interblocks.iwallet.smb.services;

//import com.hitachidps.iwallet.smb.model.dto.*;
//import com.hitachidps.iwallet.smb.service.wsClient.VishwaClientService;
//import com.hitachidps.iwallet.smb.wsdl.vishwa.DoLoginResponse;
import com.interblocks.iwallet.adaptor.rest.admin.IAdminRestClient;
import com.interblocks.iwallet.adaptor.rest.admin.model.GetUserRes;
import com.interblocks.iwallet.adaptor.rest.admin.model.UserRes;
import com.interblocks.iwallet.oauth.PCIExtract;
import com.interblocks.iwallet.oauth.ResourceOwnerPasswordCredentialsGrant;
import com.interblocks.iwallet.smb.model.dto.*;
import com.interblocks.iwallet.smb.rest.SMBRestClient;
import com.interblocks.iwallet.smb.rest.dto.NicValidateRes;
import com.interblocks.iwallet.smb.services.wsClient.VishwaClientService;
import com.interblocks.iwallet.smb.wsdl.vishwa.DoLoginResponse;
import com.interblocks.iwallet.subcomponents.application.ApplicationService;
import com.interblocks.iwallet.subcomponents.cache.CacheService;
import com.interblocks.iwallet.subcomponents.device.DeviceService;
import com.interblocks.iwallet.subcomponents.device.domain.Device;
import com.interblocks.iwallet.subcomponents.kyc.KycService;
import com.interblocks.iwallet.subcomponents.kyc.db.model.CmnDrKyc;
import com.interblocks.iwallet.subcomponents.kyc.db.model.WltDrUsrKycStat;
import com.interblocks.iwallet.subcomponents.kyc.util.KycTypes;
import com.interblocks.iwallet.subcomponents.user.UserService;
import com.interblocks.iwallet.subcomponents.user.db.model.WltDmUsrRegReq;
import com.interblocks.iwallet.subcomponents.user.db.repository.UserRepository;
import com.interblocks.iwallet.subcomponents.user.domain.User;
import com.interblocks.iwallet.util.DBSequenceManager;
import com.interblocks.iwallet.util.ServiceValidator;
import com.interblocks.webtools.core.util.mapper.DataModelMapper;
import com.interblocks.webtools.statemachine.service.FsmService;
import com.interblocks.webtools.statemachine.util.StateMachine;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.threeten.bp.DateTimeUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class VishwaServiceImpl implements VishwaService{


    private static final String CIPER_METHOD = "AES/CBC/PKCS5PADDING";

    @Autowired
    VishwaClientService vishwaClientService;

    @Autowired
    IAdminRestClient iAdminRestClient;

    @Autowired
    SMBRestClient smbRestClient;

    @Autowired
    FsmService fsmService;

    @Autowired
    PCIExtract pciExtract;
    @Autowired
    UserRepository userRepository;

    @Autowired
    DBSequenceManager dbSequenceManager;

    @Autowired
    DataModelMapper dataModelMapper;

    final UserService userService;
    final DeviceService deviceService;
    final ResourceOwnerPasswordCredentialsGrant passwordCredentialsGrant;
    final ApplicationService applicationService;
    final KycService kycService;
    final ServiceValidator serviceValidator;
    final CacheService cacheService;

    public VishwaServiceImpl(UserService userService, DeviceService deviceService, ResourceOwnerPasswordCredentialsGrant passwordCredentialsGrant, ApplicationService applicationService, KycService kycService, ServiceValidator serviceValidator, PCIExtract pciExtract, CacheService cacheService) {
        this.userService = userService;
        this.deviceService = deviceService;
        this.passwordCredentialsGrant = passwordCredentialsGrant;
        this.applicationService = applicationService;
        this.kycService = kycService;
        this.serviceValidator = serviceValidator;
        this.cacheService = cacheService;
    }

    @Value("${api.response_datetime_format:MM/dd/yyyy HH:mm:ss}")
    String resDateTimeFormat;

    @Value("${defaultBankCode}")
    String defaultBankCode;


    VishwaLoginResponse validateVishwaUser(String vishwaId, String password){
        DoLoginResponse doLoginResponse=vishwaClientService.vishwaLogin(vishwaId,password);
        if (doLoginResponse!=null&&doLoginResponse.getDoLoginReturn().getStatusCode().equals("0")){
            VishwaLoginResponse vishwaLoginResponse=new VishwaLoginResponse();
            vishwaLoginResponse.setNic(doLoginResponse.getDoLoginReturn().getNic());
            vishwaLoginResponse.setPassport(doLoginResponse.getDoLoginReturn().getPassport());
            vishwaLoginResponse.setUserName(doLoginResponse.getDoLoginReturn().getUserName());
            vishwaLoginResponse.setMobileNo(doLoginResponse.getDoLoginReturn().getMobileNo());
            System.out.println("vishwaLoginResponse---------- "+vishwaLoginResponse);
            return vishwaLoginResponse;
        }
        System.out.println("return null;----------");
        return null;
    }


    @Override
    public ResponseLoginUser vishwaLogin(String clientId, String clientSecret, VishwaLoginRequest loginUser) throws Exception{

        String vishwaId=loginUser.getVishwaId();
        System.out.println("vishwaId---------- "+vishwaId);
        System.out.println("-------------------------"+clientId+" "+clientSecret+" "+loginUser.getPassword());
        System.out.println("validateApplication---------- "+applicationService.validateApplication(clientId, clientSecret));
        String password = applicationService.decrypt(clientId, clientSecret, loginUser.getPassword());
        System.out.println("password---------- "+password);

        VishwaLoginResponse vishwaLoginResponse=new VishwaLoginResponse();

        DoLoginResponse doLoginResponse=vishwaClientService.vishwaLogin(vishwaId,password);
        if (doLoginResponse!=null&&doLoginResponse.getDoLoginReturn().getStatusCode().equals("0")){
//            vishwaLoginResponse.setNic(doLoginResponse.getDoLoginReturn().getNic());
//            vishwaLoginResponse.setPassport(doLoginResponse.getDoLoginReturn().getPassport());
//            vishwaLoginResponse.setUserName(doLoginResponse.getDoLoginReturn().getUserName());
//            vishwaLoginResponse.setMobileNo(doLoginResponse.getDoLoginReturn().getMobileNo());
//            System.out.println("vishwaLoginResponse---------- "+vishwaLoginResponse);
        }else{
            return null;
        }


        WltDmUsrRegReq wltDmUsrRegReq = userService.findUserBySocialIdAndProvider(vishwaId, "VISHWA");

//        if(wltDmUsrRegReq==null){
//
//            NicValidateRes nicValidateRes=validateNic(doLoginResponse.getDoLoginReturn().getNic());
//
//            if (nicValidateRes!=null&&nicValidateRes.getVishwa().equalsIgnoreCase("Y")){
//                String userId = pciExtract.getNextWalletId();
//
//                Device device = new Device();
//                device.setAndroidSignature(loginUser.getDeviceInfo().getAndroidSignature());
//                device.setImei(loginUser.getDeviceInfo().getImei());
//                device.setSerialNumber(loginUser.getDeviceInfo().getSerialNumber());
//                device.setType(loginUser.getDeviceInfo().getType());
//                device.setUuID(loginUser.getDeviceInfo().getUuID());
//                device.setDeviceName(loginUser.getDeviceInfo().getDeviceName());
//                deviceService.addLoginDevice(userId, device, "1", true);
//
//                PostUser postUser=new PostUser();
//                postUser.setFirstName(nicValidateRes.getCustfullname().split(" ")[0]);
//                postUser.setLastName(nicValidateRes.getCustfullname().split(" ")[1]);
//                postUser.setCity(nicValidateRes.getPermcity_desc());
//                postUser.setCountry(nicValidateRes.getPermcntry_desc());
//                postUser.setDeviceInfo(loginUser.getDeviceInfo());
//                postUser.setDeviceID(loginUser.getDeviceID());
//                postUser.setDob(new BigDecimal(new SimpleDateFormat("dd-MM-yyyy").parse(nicValidateRes.getCustdob()).getTime()));
//                postUser.setEmail(nicValidateRes.getEmail());
//                postUser.setGender(nicValidateRes.getGender());
//                postUser.setMobile(nicValidateRes.getSmsNumber());
//                postUser.setNationality(nicValidateRes.getCustfullname());
//                postUser.setNicNumber(doLoginResponse.getDoLoginReturn().getNic());
////                user.setPassport(nicValidateRes.get());
//                postUser.setStreetNo(nicValidateRes.getPermaddrln1());
//                postUser.setTitle(nicValidateRes.getCusttitle());
//
//                User user = new User();
//                user = dataModelMapper.apiToDB(postUser, user);
//
//                createUserRecord(userId,user);
//
//                List<AcctInfoData> accList=doLoginResponse.getDoLoginReturn().getAccountList();
//
//                int index=0;
//                if (!accList.isEmpty()){
//                    for (AcctInfoData acc:accList){
//                        instrumentRepository
//                        WltDmUsrAcct defaultAccount = new WltDmUsrAcct();
//                        log.debug("Starting Get id from WLT_DQ_DM_USR_ACCT");
//                        long idNextVal = dbSequenceManager.getNextValue("WLT_DQ_DM_USR_ACCT");
//                        log.debug("End WLT_DQ_DM_USR_ACCT id " + idNextVal);
//                        defaultAccount.setInstrmntId(new BigDecimal(idNextVal));
//                        defaultAccount.setAcctName("AGNT");
//
//                        defaultAccount.setUsrId(userId);
//                        defaultAccount.setAddBy("VishwaService");
//                        defaultAccount.setAddDate(new Date());
//                        defaultAccount.setAlias("Vishwa Account "+index);
//                        defaultAccount.setBnkCode(defaultBankCode);
//                        defaultAccount.setCoId("IBNK");
//                        defaultAccount.setCrdBin("");
//                        defaultAccount.setCrdNo(pciExtract.EncryptString(acc.getAcctNo().trim()));
//                        defaultAccount.setCrdTyp(Integer.toString(AccountType.ACCOUNT.getValue()));
//                        defaultAccount.setEml(userId);
//                        defaultAccount.setExpMonth(0);
//                        defaultAccount.setExpYear(0);
//
//
//                        defaultAccount.setIsCrd(0);
//                        defaultAccount.setIsDef(0);
//                        defaultAccount.setIsVishwa(1);
//                        defaultAccount.setMdfyBy("VishwaService");
//                        defaultAccount.setMdfyDate(new Date());
//
//                        defaultAccount.setMercId(null);
//                        defaultAccount.setTermId(null);
//
//                        defaultAccount.setNameOnCrd(firstName + " " + lastName);
//                        defaultAccount.setStat(CardRegisterStatus.ACTIVE.getValue());
//                        defaultAccount.setUsrId(userId);
//                        defaultAccount.setVsblty(1);
//
//                        instrumentRepository.save(defaultAccount);
//                    }
//                }
//
//
//            }
//
//
//
//
//
//
//
////            Device device = userServiceFacade.getCoreDeviceInfo(loginUser.getDeviceInfo());
//            String tempToken = userServiceFacade.createUserWithLogin(userId, user, clientID, clientSecret, device);
//            ResponsePostUser responsePostUser = userServiceFacade.getUser(userId);
//            responsePostUser.setToken(tempToken);
//        }else{
//
//        }

        String userId=wltDmUsrRegReq.getUsrId();
        System.out.println("userId---------- "+userId);

        String respToken = validateCredentials(vishwaId,wltDmUsrRegReq.getUsrId(), clientId, clientSecret, password);
        System.out.println("respToken---------- "+respToken);
//        WltDmUsrRegReq wltDmUsrRegReq = userService.getUserByUserId(userId, defaultBankCode);

        if (respToken!=null){
            return validateDeviceAndGetTokenRes(userId, clientId, respToken, loginUser.getDeviceID(), loginUser.getDeviceInfo(), loginUser.getPassword(), wltDmUsrRegReq);
        }
        return null;

    }

    private void createUserRecord(String userId, User user) {

        Date addedDate = new Date();
        String email = user.getEmail();
        String mobile = user.getMobileNo();

        WltDmUsrRegReq insertwltuser = new WltDmUsrRegReq();
        insertwltuser.setSessionId(user.getRequestId());
        insertwltuser.setAddedDate(addedDate);
        insertwltuser.setAddedBy(userId);
        insertwltuser.setEmail((email != null && !email.isEmpty()) ? email.toUpperCase() : "NA");
        insertwltuser.setModifiedBy(userId);
        insertwltuser.setModifiedDate(addedDate);
        insertwltuser.setMoblNo(mobile.trim());
        insertwltuser.setBankCode(defaultBankCode);
        insertwltuser.setUsrId(userId);
        insertwltuser.setFirstName(user.getFirstName());
        insertwltuser.setLastName(user.getLastName());
        insertwltuser.setGender(user.getGender());
        insertwltuser.setDob(new Date(user.getDob().longValue()));
        insertwltuser.setUserName(user.getUserName());
        insertwltuser.setNic(user.getNic());
        insertwltuser.setCustomerNo(null);
        insertwltuser.setCountry(user.getCountry());
        insertwltuser.setCity(user.getCity());
        insertwltuser.setStreetAddress(user.getAddress1());
        insertwltuser.setPostalCode(user.getPostalCode());
        insertwltuser.setLatitude(user.getLatitude());
        insertwltuser.setLongitude(user.getLongitude());

        int sequenceNumber = dbSequenceManager.getNextValue("WLT_DQ_DM_USR_REG_REQ");
        insertwltuser.setUsrRegId(BigDecimal.valueOf(sequenceNumber));

        Integer initialStateValue = fsmService.getInitialState(StateMachine.USER).getSecond();
        insertwltuser.setStatus(initialStateValue.toString());

//        WltDpUsrRegReq pendingUser = mapPendingWalletUser(insertwltuser);
        userRepository.save(insertwltuser);
//        pendingUserRepository.save(pendingUser);
    }

    @Override
    public NicValidateRes validateNic(String nicType,String nic) throws Exception {
        return smbRestClient.validateNic(nicType,nic);
    }

    public String validateCredentials(String vishwaId,String userId, String clientId, String clientSecret, String pwdPlain) {
//        String encryptedPassword = this.pciExtract.EncryptString(pwdPlain);
        VishwaLoginResponse loginResponse=validateVishwaUser(vishwaId, pwdPlain);
        System.out.println("validateVishwaUser----------");

        if (loginResponse!=null){
            return this.generateLoginToken(userId, clientId, clientSecret);
        }
        return null;
    }

    private String generateLoginToken(String userId, String clientId, String clientSecret) {
        List<String> authorities = this.getUserAuthorities(userId);
        System.out.println("authorities---------- ");
        return this.passwordCredentialsGrant.generateToken(userId, clientId, clientSecret, authorities);
    }

    public List<String> getUserAuthorities(String userId) {
        List<String> authorities = new ArrayList<>();

        GetUserRes walletUser = iAdminRestClient.getWalletUser(userId, defaultBankCode);
        UserRes userRes = walletUser.getUserDetails();
        if (userRes.getIsMerchant() != null && userRes.getIsMerchant().equalsIgnoreCase("1")) {
            authorities.add("MERCHANT_ROLE");
        }
        if (userRes.getIsAgent() != null && userRes.getIsAgent().equalsIgnoreCase("1")) {
            authorities.add("AGENT_ROLE");
        }
        return authorities;
    }

    private ResponseLoginUser validateDeviceAndGetTokenRes(String userId, String clientId, String respToken, String deviceID, LoginUserDeviceInfo deviceInfo, String password, WltDmUsrRegReq wltDmUsrRegReq) {
        ResponseLoginUser loginResp = new ResponseLoginUser();
        cacheService.updateNewActiveLogin(userId, respToken);

        System.out.println("validateDeviceAndGetTokenRes-----");

        deviceService.addFirebaseDeviceSub(deviceID, defaultBankCode, userId, respToken);
        System.out.println("addFirebaseDeviceSub-----");
        Device device = getCoreDeviceInfo(deviceInfo);
        System.out.println("getCoreDeviceInfo-----");
        if (!deviceService.isVerifiedDevice(userId, device)) {
            System.out.println("new device login-----");
            // new device login
            int deviceSeq = deviceService.addLoginDevice(userId, device, "5", false);
            System.out.println("deviceSeq----- "+deviceSeq);
            String sessionId = userService.requestNewDeviceOtpSession(userId, null);
            System.out.println("sessionId----- "+sessionId);
            respToken = passwordCredentialsGrant.generateOtpOnlyToken(userId, password, clientId, deviceSeq);
            System.out.println("respToken----- "+respToken);
            loginResp.setNewDevice(true);
            System.out.println("loginResp.setNewDevice-----");
            ModelUser modelUser = new ModelUser();
            modelUser.setMobile(wltDmUsrRegReq.getMoblNo());
            modelUser.setGender(wltDmUsrRegReq.getGender());
            loginResp.setUser(modelUser);
            System.out.println("ModelUser----- ");
            loginResp.setSessionID(sessionId);
            System.out.println("!deviceService.isVerifiedDevice(userId, device)-----");
        } else {
            // device verified. responding token with user object
            User userResponse = getUserResponseById(userId);
            System.out.println("userResponse----- "+userResponse);
            loginResp.setNewDevice(false);
            loginResp.setUser(getUserObjFromUserResponse(userResponse));
        }
        System.out.println("respTokensss-----"+respToken);
        loginResp.setToken(respToken);
        return loginResp;
    }

    public User getUserResponseById(String userId) {
        return userService.getUserDetailsByIdActivityEnabled(userId);
    }

    private ModelUser getUserObjFromUserResponse(User userResponse) {
        ModelUser usrObj = new ModelUser();

        UserRegistrationProcess registrationProcess = new UserRegistrationProcess();
        registrationProcess.setCompleted(userResponse.isRegistrationCompleate());
        registrationProcess.setStep(userResponse.getStep());
        usrObj.setRegistrationProcess(registrationProcess);

        usrObj.setUserID(userResponse.getUserId().toUpperCase());
        usrObj.setEmail(userResponse.getEmail());
        usrObj.setFirstName(userResponse.getFirstName());
        usrObj.setLastName(userResponse.getLastName());
        usrObj.setMobile(userResponse.getMobileNo());
        usrObj.setBankCode(userResponse.getBankCode());
        usrObj.setImageUrl(userResponse.getImageUrl());
        usrObj.setTitle(userResponse.getTitle());
        usrObj.setStreetNo(userResponse.getAddress1());
        usrObj.setCity(userResponse.getCity());
        usrObj.setCountry(userResponse.getCountry());
        usrObj.setGender(userResponse.getGender());
        usrObj.setNicNumber(userResponse.getNic());
        usrObj.setTocStatus(userResponse.isTocStatus());
        usrObj.setForcePassword(userResponse.isForcePassword());
        usrObj.setDob(userResponse.getDob().toString()); //
        usrObj.setUserName(userResponse.getUserName()); //
        usrObj.setCountry(userResponse.getCountry()); //
        usrObj.setNationality(userResponse.getNationality()); //
        usrObj.setMerchantAccountID(userResponse.getMerchantAccountId()); //

        SimpleDateFormat sdf = new SimpleDateFormat(resDateTimeFormat);
        usrObj.setRegistrationDate(sdf.format(DateTimeUtils.toDate(userResponse.getAddedDate().toInstant())));

        List<CmnDrKyc> requiredKycDocs = kycService.getRequiredKycDocs();
        List<WltDrUsrKycStat> uploadedList = kycService.getUploadedDocumentByUser(userResponse.getUserId().toUpperCase());

        Map<KycTypes, List<WltDrUsrKycStat>> kycMap = kycService
                .getUploadedKycForUser(requiredKycDocs, uploadedList);

        List<KYC> userKycList = kycMap.get(KycTypes.USER).stream()
                .map(this::mapFromKycStat).collect(Collectors.toList());

        List<KYC> merchantKycList = kycMap.get(KycTypes.MERCHANT).stream()
                .map(this::mapFromKycStat).collect(Collectors.toList());

        List<KYC> agentKycList = kycMap.get(KycTypes.AGENT).stream()
                .map(this::mapFromKycStat).collect(Collectors.toList());

        usrObj.setUserKYCList(userKycList);
        usrObj.setMerchantKYCList(merchantKycList);
        usrObj.setAgentKYCList(agentKycList);

        usrObj.setKycApproved(kycService.checkRequiredApproved(KycTypes.USER, requiredKycDocs, uploadedList));
        usrObj.setMerchantEnable(kycService.checkRequiredApproved(KycTypes.MERCHANT, requiredKycDocs, uploadedList));
        usrObj.setAgentEnable(kycService.checkRequiredApproved(KycTypes.AGENT, requiredKycDocs, uploadedList));
        return usrObj;
    }

    private KYC mapFromKycStat(WltDrUsrKycStat item) {
        KYC kycDoc = new KYC();
        kycDoc.setStatus(item.getStatus());
        kycDoc.setId(String.valueOf(item.getUsrKycId()));
        kycDoc.setUploaded(true);
        kycDoc.setKycType(item.getFileType());
        kycDoc.setComment(item.getComment());
        return kycDoc;
    }

    public Device getCoreDeviceInfo(LoginUserDeviceInfo deviceInfo) {
        Device device = new Device();
        device.setAndroidSignature(deviceInfo.getAndroidSignature());
        device.setImei(deviceInfo.getImei());
        device.setSerialNumber(deviceInfo.getSerialNumber());
        device.setType(deviceInfo.getType());
        device.setUuID(deviceInfo.getUuID());
        device.setDeviceName(deviceInfo.getDeviceName());
        return device;
    }

}
