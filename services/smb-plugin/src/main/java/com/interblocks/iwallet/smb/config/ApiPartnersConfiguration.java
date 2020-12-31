package com.interblocks.iwallet.smb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "smb.providers.iAdmin")
public class ApiPartnersConfiguration {

    private User user;
    private Merchant merchant;
    private Recovery recovery;

    @Data
    public static class Merchant {
        private String initUrl;
        private String validateUrl;
        private String initMerchantUserRegUrl;
        private String merchantInitUrl;
        private String uiDataUrl;
        private String configDataUrl;
        private String completeMerchRegUrl;
        private String activityDetailsUrl;
    }

    @Data
    public static class User {
        private String initCreateMerchUrl;
        private String createMerchUserUrl;
        private String initInternalMerchUserUrl;
        private String createInternalMerchUserUrl;
        private String editInternalMerchUserUrl;
        private String changePassUrl;
        private String userInfoUrl;
        private String status;
    }

    @Data
    public static class Recovery {
        private String passwordRecoverUrl;
        private String passwordRecoveryValidationUrl;
        private String twoFactorOtpUrl;
        private String resetMerchPassUrl;
    }
}
