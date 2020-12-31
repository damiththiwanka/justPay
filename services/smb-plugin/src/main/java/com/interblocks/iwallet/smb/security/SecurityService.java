package com.interblocks.iwallet.smb.security;

import com.interblocks.iwallet.oauth.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {
    public String getUserName() {
        UserPrincipal principal = this.getPrinciple();
        return principal.getId();
    }

    public String getIpgMerchantId() {
        UserPrincipal userPrinciple = this.getPrinciple();
        return userPrinciple.getIpgMerchantId();
    }

    public String getBankCode() {
        UserPrincipal userPrinciple = this.getPrinciple();
        return userPrinciple.getBankCode();
    }

    public UserPrincipal getPrinciple() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
