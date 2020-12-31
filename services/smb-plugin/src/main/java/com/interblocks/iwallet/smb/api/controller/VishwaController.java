package com.interblocks.iwallet.smb.api.controller;


//import com.hitachidps.iwallet.smb.model.dto.VishwaLoginRequest;
//import com.hitachidps.iwallet.smb.service.VishwaService;
import com.interblocks.iwallet.smb.model.dto.ResponseLoginUser;
import com.interblocks.iwallet.smb.model.dto.ResponseSMBDefault;
import com.interblocks.iwallet.smb.model.dto.VishwaLoginRequest;
import com.interblocks.iwallet.smb.services.VishwaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;

@RestController
@RequestMapping("/vishwa")
@Log4j2
public class VishwaController {

    @Autowired
    VishwaService vishwaService;

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseSMBDefault vishwaLogin(@RequestBody VishwaLoginRequest login
            , @QueryParam("clientID") String clientID
            , @QueryParam("clientSecret") String clientSecret)  throws NotFoundException {
//        log.debug("--------------getBankList--------------");
        try {
//            UserPrincipal principal = (UserPrincipal) securityContext.getUserPrincipal();


//            return ResponseCreator.successfulResponse(vishwaService.vishwaLogin(clientID,clientSecret,login));
            ResponseLoginUser loginUser=vishwaService.vishwaLogin(clientID,clientSecret,login);
            if (loginUser!=null){
                return new ResponseSMBDefault().status(200).entity(loginUser);
            }
            return new ResponseSMBDefault().status(401).entity(null);

        }catch (Exception e){
            log.error("Exception",e);
            return new ResponseSMBDefault().status(401).entity(null);
//            Response.status(401).entity(null).build();
        }

    }

    @PostMapping(value = "/nominatePassword", consumes = "application/json", produces = "application/json")
    public ResponseSMBDefault nominatePassword(@RequestBody VishwaLoginRequest login
            , @QueryParam("clientID") String clientID
            , @QueryParam("clientSecret") String clientSecret)  throws NotFoundException {
//        log.debug("--------------getBankList--------------");
        try {
//            UserPrincipal principal = (UserPrincipal) securityContext.getUserPrincipal();


//            return ResponseCreator.successfulResponse(vishwaService.vishwaLogin(clientID,clientSecret,login));
            ResponseLoginUser loginUser=vishwaService.vishwaLogin(clientID,clientSecret,login);
            return new ResponseSMBDefault().status(200).entity(loginUser);
        }catch (Exception e){
            log.error("Exception",e);
            return new ResponseSMBDefault().status(401).entity(null);
//            Response.status(401).entity(null).build();
        }

    }

}
