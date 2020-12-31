package com.interblocks.iwallet.smb.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.net.URI;

@Configuration
@ComponentScan(basePackages = {
        "com.interblocks.iwallet.smb.configuration",
        "com.interblocks.iwallet.smb.config",
        "com.interblocks.iwallet.adaptor.rest.admin",
        "com.interblocks.iwallet.smb",
        "com.interblocks.iwallet.oauth",
        "com.interblocks.iwallet.config.core",
        "com.interblocks.iwallet.config.database",
        "com.interblocks.iwallet.isodetails",
        "com.interblocks.iwallet.service",
        "com.interblocks.iwallet.api.qrcode",
        "com.interblocks.iwallet.subcomponents",
        "com.interblocks.iwallet.util",
        "com.interblocks.iwallet.adaptor",
        "com.interblocks.iwallet.repository",
        "com.interblocks.webtools",
        "io.swagger",
})
@EnableJpaRepositories(basePackages = {
        "com.interblocks.webtools",
        "com.interblocks.iwallet.subcomponents",
        "com.interblocks.iwallet.repository"
})
public class PartnersSpringWebConfig {
    @Value("${smb-plugin.log.file:./logs/smb-plugin.log}")
    private String logFile;
    @Value("${smb-plugin.log.pattern:./logs/archive/smb-plugin-%d{MM-dd-yy-HH-mm-ss}-%i.log.gz}")
    private String logPattern;
    @Value("${smb-plugin.log.level:INFO}")
    private String logLevel;

    @PostConstruct
    private void configureLogging() {
        System.setProperty("logFilename", logFile);
        System.setProperty("logFilePattern", logPattern);
        System.setProperty("logLevel", logLevel);

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.setConfigLocation(URI.create("log4j2.properties"));
        ctx.reconfigure();
    }

    @Bean
    public OpenAPI partnerApi() {
        final String schemeName = "BearerToken";

        final Components components = new Components();
        components.addSecuritySchemes(schemeName, new SecurityScheme()
                .name(schemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(schemeName)).components(components)
                .info(new Info().title("Api Partners").license(new License().name("CLOSED")).version("0.0.1"));
    }
}
