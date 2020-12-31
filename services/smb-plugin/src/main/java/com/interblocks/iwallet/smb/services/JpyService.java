package com.interblocks.iwallet.smb.services;

import com.interblocks.iwallet.smb.util.jpy.JPBank;

import javax.xml.ws.Response;
import java.util.List;


public interface JpyService {

    List<JPBank> getBankList();
//    Response registerAccount(PostAccount account);
}
