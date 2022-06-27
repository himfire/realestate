package com.wrfxx.demo10.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrfxx.demo10.domain.value.ApiResponse;
import com.wrfxx.demo10.domain.value.MESSAGE;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AccessDeniedException e)
            throws IOException, ServletException {
        ApiResponse response = new ApiResponse(403, "Access Denied");
        response.setMessage(MESSAGE.ACCESS_DENIED);

        httpServletResponse.addHeader("WWW-Authenticate", "Basic realm= + getRealmName() + ");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response);
        out.flush();
    }
}
