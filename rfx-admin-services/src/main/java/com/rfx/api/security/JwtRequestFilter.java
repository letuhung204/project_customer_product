


package com.rfx.api.security;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rfx.api.controller.BusinessUnitController;
import com.rfx.api.service.RfxAdminService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Component will autodetect these classes for dependency injection
 * when annotation-based configuration and classpath scanning is used
 * @author Hubino
 * @version 1.0
 * @since 06/04/2020
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private RfxAdminService recruitBusinessService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    BusinessUnitController businessUnitController;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization,Origin");
        String useremail = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                useremail = jwtTokenUtil.getUserEmailFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException ex) {
                long expi = (ex.getClaims().getExpiration().getTime())/1000l;
                long diff = System.currentTimeMillis()/1000l - expi;
                System.out.println(diff+"----"+expi);
                if(diff < 900) {
                    System.out.println("JWT Token has expired:"+ex.getClaims().getSubject());
                    request.setAttribute("expired", ex.getMessage());
                    request.setAttribute("username", ex.getClaims().getSubject());
                } else {
                    System.out.println("JWT Token has expired:"+ex.getClaims().getSubject());
                    request.setAttribute("Unauthorized", ex.getMessage());
                }

            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (useremail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.recruitBusinessService.loadUserByUsername(useremail);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                this.recruitBusinessService.setAuthorizationUserEmail(useremail,jwtToken);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}