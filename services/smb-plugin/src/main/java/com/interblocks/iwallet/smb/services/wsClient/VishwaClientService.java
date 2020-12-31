package com.interblocks.iwallet.smb.services.wsClient;

//import com.hitachidps.iwallet.smb.wsdl.vishwa.DoLogin;
//import com.hitachidps.iwallet.smb.wsdl.vishwa.DoLoginResponse;
import com.interblocks.iwallet.smb.wsdl.vishwa.DoLogin;
import com.interblocks.iwallet.smb.wsdl.vishwa.DoLoginResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class VishwaClientService extends WebServiceGatewaySupport {

    public DoLoginResponse vishwaLogin(String vishwaId, String password) {
        DoLogin request = new DoLogin();
        request.setUserId(vishwaId);
        request.setPassword(password);

        DoLoginResponse response = (DoLoginResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return response;
    }
}
