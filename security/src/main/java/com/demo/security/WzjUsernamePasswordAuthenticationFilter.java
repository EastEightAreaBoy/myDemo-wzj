package com.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HP
 */
//@Service
@Slf4j
public class WzjUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        log.info("======UsernamePasswordAuthenticationFilter=========");
        // 判断是否为 JSON 格式请求
//        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
//            // ...
//        } else {
            return super.attemptAuthentication(request, response);
//        }
    }
}
