package com.optimagrowth.licenseserver.contextholder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author abishek on 2022-04-03
 */
@Component
@Slf4j
public class UserContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setOrganisationId(httpServletRequest.getHeader(UserContext.ORGANISATION_ID));

        filterChain.doFilter(httpServletRequest,response);
    }
}
