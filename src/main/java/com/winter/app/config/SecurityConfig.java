package com.winter.app.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecuritySuccessHandler securitySuccessHandler;
	
	@Autowired
	private MemberService memberService;
	
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		
		return web -> web
				.ignoring()
				.antMatchers("/css/**")
				.antMatchers("/img/**")
				.antMatchers("/js/**")
				.antMatchers("/vender/**");
		
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception {
		
		httpSecurity
				.cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
					.antMatchers("/notice/add").hasRole("ADMIN")//ROLE_ADMIN에서 ROLE_제외
					.antMatchers("/manager/*").hasAnyRole("ADMIN","MANAGER")
					.antMatchers("/").permitAll()
					.and()
				//form 관련 설정
				.formLogin()
					.loginPage("/member/login") //내장된 로그인폼을 사용하지 않고, 개발자가 만든 폼을 사용하겠다, 로그인을 처리하는 주소
					.defaultSuccessUrl("/")
//					.successHandler(securitySuccessHandler)
//					.failureUrl("/member/login?message=LoginFail")
					.failureHandler(getFailHandler())
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")
//					.logoutSuccessUrl("/")
					.addLogoutHandler(getLogoutAdd())
					.logoutSuccessHandler(getLogoutHandler())
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.and()
				.rememberMe()
					.tokenValiditySeconds(60)
					.key("rememberKey")
					.userDetailsService(memberService)
					.authenticationSuccessHandler(securitySuccessHandler)
					.and()
//					.sessionManagement()
//					.and()
				.oauth2Login()
					.userInfoEndpoint()
					.userService(memberService)
					.and()
					
					
				
		;
		
		return httpSecurity.build();
		
		
	}
	
	private SecurityFailHandler getFailHandler() {
		return new SecurityFailHandler();
	}

	private SecurityLogoutAdd getLogoutAdd() {
		return new SecurityLogoutAdd();
	}
	
	private SecurityLogoutHandler getLogoutHandler() {
		
		return new SecurityLogoutHandler();
	}
}
