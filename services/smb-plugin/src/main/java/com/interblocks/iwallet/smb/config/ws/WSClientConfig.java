package com.interblocks.iwallet.smb.config.ws;

//import com.hitachidps.iwallet.smb.service.wsClient.VishwaClientService;
import com.interblocks.iwallet.smb.services.wsClient.VishwaClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSClientConfig {

    @Value("${vishwa.url}")
    String vishwaUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.interblocks.iwallet.smb.wsdl.vishwa");
        return marshaller;
    }
    @Bean
    public VishwaClientService vishwaClient(Jaxb2Marshaller marshaller) {
        VishwaClientService client = new VishwaClientService();
        client.setDefaultUri(vishwaUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
