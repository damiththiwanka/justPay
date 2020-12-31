package com.interblocks.iwallet.smb.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interblocks.iwallet.oauth.TokenCipher;
import com.interblocks.iwallet.oauth.UserPrincipal;
import com.interblocks.iwallet.smb.config.JwtConfig;
import com.interblocks.iwallet.smb.model.ResponseDefault;
import com.interblocks.iwallet.util.Const;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@Scope("prototype")
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    TokenCipher tokenCipher;


    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        log.info("Attempting to validate the Bearer token on TokenFilter...");
        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(jwtConfig.getHeader());

        // 2. validate the header and check the prefix
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            log.info("Bearer token verification failed.");
            chain.doFilter(request, response);        // If not valid, go to the next filter.
            return;
        }

        log.info("Bearer token found");
        // If there is no token provided and hence the user won't be authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a token.

        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "").trim();

        try {
            token = tokenCipher.decipherToken(token, Const.AUTH_KEY.getBytes());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return;
        }


        try {    // exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            log.info("Validate the Token");
            Claims claims = Jwts.parser()
                    .setSigningKey(Const.AUTH_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            Header headers = Jwts.parser().setSigningKey(Const.AUTH_KEY.getBytes()).parse(token).getHeader();
            log.info("Token Extracted");
            String username = (String) headers.get("UserName");
            log.info("User name {} from token", username);

            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setId((String) headers.get("UserName"));
            userPrincipal.setIpgMerchantId((String) headers.get("ipgMerchantId"));
            userPrincipal.setFirstName((String) headers.get("user_fname"));
            userPrincipal.setLastName((String) headers.get("user_lname"));
            userPrincipal.setBankCode((String) headers.get("BankCode"));
            userPrincipal.setClientId((String) headers.get("client_id"));
            userPrincipal.setClientSecret((String) headers.get("client_secret"));

            ThreadContext.put("userId", (String) headers.get("UserName"));

            if (username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) (claims.get("authorities") == null ? new ArrayList<>() : claims.get("authorities"));

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userPrincipal, headers, authorities.
                        stream().
                        map(SimpleGrantedAuthority::new).
                        collect(Collectors.toList()));


                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (ExpiredJwtException expiredException) {
            log.error("Session Expired");
            setExpiredResponse(response);
            SecurityContextHolder.clearContext();
            return;
        } catch (Exception e) {
            log.error("AUTH Token error ", e);
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        chain.doFilter(request, response);
    }

    private void setExpiredResponse(HttpServletResponse response) throws IOException {
        ResponseDefault responseDefault = new ResponseDefault();
        responseDefault.setResponseCode("440");
        responseDefault.setDescription("Session has been expired");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(responseDefault));
        response.getWriter().flush();
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
