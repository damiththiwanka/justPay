package com.interblocks.iwallet.smb.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.List;

@Log4j2
@Configuration
public class HazelcastConfig {

    @Value("${hazelcast.custom.logger:none}")
    private String logType;
    @Value("${hazelcast.custom.groupName:WLT_DEV}")
    private String cachingGroupName;
    @Value("${hazelcast.custom.instanceName:wlt_dev_caching_instance}")
    private String cachingInstanceName;
    @Value("${hazelcast.custom.port:1055}")
    private Integer cachingPort;
    @Value("#{'${hazelcast.custom.ips}'.split(',')}")
    private List<String> cachingScanIpList;
    @Value("#{'${hazelcast.custom.interfaces}'.split(',')}")
    private List<String> cachingBindInterfaceList;

    @Bean(destroyMethod = "shutdown")
    public HazelcastInstance getHazelcast() {
        return com.hazelcast.core.Hazelcast.getOrCreateHazelcastInstance(hazelCastConfig());
    }

    private Config hazelCastConfig() {
        Config config = new Config();
        config.setInstanceName(cachingInstanceName);
        config.getGroupConfig().setName(cachingGroupName);
        //password is not validated since v3.11
        config.setProperty("hazelcast.application.validation.token", "iWallet@123456");
        config.getNetworkConfig().setPort(cachingPort);
        config.getNetworkConfig().setPortAutoIncrement(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().setReuseAddress(true);

        System.setProperty("hazelcast.socket.bind.any", "false");
        System.setProperty("hazelcast.socket.client.bind.any", "false");
        System.setProperty("hazelcast.socket.server.bind.any", "false");
        System.setProperty("hazelcast.logging.type", logType);

        log.info("Network Config : {}", config.getNetworkConfig().toString());
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("Host Address   : {}", hostAddress);
            config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(hostAddress);
            cachingScanIpList.forEach(ip -> {
                config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(ip);
            });
            cachingBindInterfaceList.forEach(interfaceIp -> {
                config.getNetworkConfig().getInterfaces().addInterface(interfaceIp);
            });

        } catch (Exception ex) {
            log.error("Error in creating new instance,", ex);
        }
        return config;
    }
}
