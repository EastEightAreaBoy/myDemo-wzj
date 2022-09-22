package com.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author HP
 */
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String sign = JWT.create()
                .withSubject("")
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("汉字123", 123)
                .sign(Algorithm.HMAC256("miyaojiami"));
        Map<String, String> result = new HashMap<>(8);
        result.put("data", "登陆成功");
        result.put("sign", sign);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        //修改编码格式
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        //输出结果
        response.getWriter().write(s);
    }
}
