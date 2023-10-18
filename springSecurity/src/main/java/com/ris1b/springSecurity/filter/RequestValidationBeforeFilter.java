package com.ris1b.springSecurity.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationBeforeFilter implements Filter {

    public static final String AUTHENTICATION_SCHEME_BASIC="Basic";
    private Charset credentialsCharSet = StandardCharsets.UTF_8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String header = req.getHeader("AUTHORIZATION");

        if(header != null){
            header = header.trim();
            if(StringUtils.startsWithIgnoreCase(header,  AUTHENTICATION_SCHEME_BASIC)){
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try {
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, credentialsCharSet);
                    int delim = token.indexOf(":");
                    if(delim == -1){
                        throw new BadCredentialsException("Invalid Basic Authentication Token");
                    }
                    String email = token.substring(0, delim);
                    if(email.toLowerCase().contains("test")) {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                }
                catch (Exception e){
                    throw new BadCredentialsException("Failed to decode basic authentication");
                }
            }
        }
        chain.doFilter(request, response);
    }
}
