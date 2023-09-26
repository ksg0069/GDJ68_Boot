package com.winter.app.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("========== auth {} =============", authentication);
		
		
		log.info("========== pathInfo {} =============", request.getPathInfo());
		
		log.info("========== requestURL {} =============", request.getRequestURL());
		
		log.info("========== requestURI {} =============", request.getRequestURI());
		
		
		
		response.sendRedirect("/");
		
	}
	
	

}
