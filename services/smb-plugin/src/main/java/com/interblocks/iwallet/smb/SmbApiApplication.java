package com.interblocks.iwallet.smb;

import com.interblocks.iwallet.smb.config.ApiPartnersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {ApiPartnersConfiguration.class})
public class SmbApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmbApiApplication.class, args);
    }
}
