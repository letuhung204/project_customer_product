

package com.rfx.api.security;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.rfx.api.model.ErrorResponse;
import com.rfx.api.service.RfxAdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Autowired
    private RfxAdminService businessUnitService;

    @Autowired
    private Environment env;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        final String expiredMsg = (String) request.getAttribute("expired");
        final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
        if(msg.equalsIgnoreCase("Unauthorized")) {
            response.setContentType("application/json");
            response.setStatus((HttpServletResponse.SC_UNAUTHORIZED));
            response.getWriter().write(getErrorResponseEntity(msg, 401,null));
        } else {
            final String username = (String) request.getAttribute("username");
            String refreshToken = businessUnitService.generateRefreshToken(Long.parseLong(env.getProperty("refresh.token.expired")),username);
            response.setContentType("application/json");
            response.setStatus((HttpServletResponse.SC_FORBIDDEN));
            response.getWriter().write(getErrorResponseEntity(msg, 403, refreshToken));
        }

    }

    public String getErrorResponseEntity(String errorMessage, int errorCode, String refreshToken) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErroMessage(errorMessage);
        errorResponse.setErroCode(errorCode);
        JSONObject jsonObject = new JSONObject(errorResponse);
        if(errorCode == 403)
            jsonObject.put("refreshToken",refreshToken);
        return jsonObject.toString();
    }



}