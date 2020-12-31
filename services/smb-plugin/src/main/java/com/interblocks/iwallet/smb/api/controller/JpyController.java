package com.interblocks.iwallet.smb.api.controller;

//import com.hitachidps.iwallet.smb.service.JpyService;
import com.interblocks.iwallet.smb.model.dto.ResponseSMBDefault;
import com.interblocks.iwallet.smb.services.JpyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpy")
@Log4j2
public class JpyController {

    @Autowired
    JpyService jpyService;

    @GetMapping("/bankList")
    public ResponseSMBDefault getBankList() {
        log.debug("--------------getBankList--------------");
        try {

            return new ResponseSMBDefault().status(200).entity(jpyService.getBankList());
        }catch (Exception e){
            log.error("Exception",e);
            return new ResponseSMBDefault().status(401).entity(null);
        }

    }


//    @PostMapping(value = "/registerAccount", consumes = "application/json", produces = "application/json")
//    public Response registerAccount(@RequestBody PostAccount account{
//        return delegate.registerAccount(account);
//
//    }



}
