package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SecurityFailHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
	
		log.info("========== Exception : {} =============", exception);
		String message="로그인실패";
		
		if(exception instanceof InternalAuthenticationServiceException) {
			
			message ="아이디 없다";
		}
		
		if(exception instanceof BadCredentialsException) {
			
			message = "비번이 틀림";
		}
		
		if(exception instanceof AccountExpiredException) {
			
			message = "계정 유효기간이 만료 관리자에게 문의";
		}
		
		if(exception instanceof LockedException) {
			
			message = "계정이 잠김 관리자에게 문의";
		}
		
		if(exception instanceof DisabledException) {
			/* isEnabled = false */
			message="휴면 계정";
		}
		
		if(exception instanceof AuthenticationCredentialsNotFoundException) {
			/* 기타 사유 로 인해 */
			message="인증이 안됨";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("/member/login?message="+ message);
		
	}
	
	

}
