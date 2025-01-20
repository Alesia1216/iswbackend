package net.ausiasmarch.iswart.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.ausiasmarch.iswart.service.JWTService;

@Component
public class JWTFilter implements Filter {

    @Autowired
    JWTService oJWTService;

    @Override
    public void doFilter(ServletRequest oServletRequest,
            ServletResponse oServletResponse,
            FilterChain oFilterChain)
            throws IOException, ServletException {

        HttpServletRequest oHttpServletRequest = (HttpServletRequest) oServletRequest;
        HttpServletResponse oHttpServletResponse = (HttpServletResponse) oServletResponse;

        if ("OPTIONS".equals(oHttpServletRequest.getMethod())) {
            oHttpServletResponse.setStatus(HttpServletResponse.SC_OK);
            oFilterChain.doFilter(oServletRequest, oServletResponse);
        } else {
            String sToken = oHttpServletRequest.getHeader("Authorization");
            if (sToken == null) {
                oFilterChain.doFilter(oServletRequest, oServletResponse);
            } else {
                if (!sToken.startsWith("Bearer ")) {
                    oHttpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                    return;
                } else {
                    String sTokenReal = sToken.substring(7);
                    String email = oJWTService.validateToken(sTokenReal);

                    if (email == null) {
                        oHttpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                        return;
                    } else {
                        oHttpServletRequest.setAttribute("email", email);
                        oFilterChain.doFilter(oServletRequest, oServletResponse);
                    }
                }
            }
        }
    }
}