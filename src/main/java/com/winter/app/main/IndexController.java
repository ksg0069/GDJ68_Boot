package com.winter.app.main;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@GetMapping("/")
	public String getIndex(HttpSession session)throws Exception{
		
//	Enumeration<String> en = session.getAttributeNames();
//	
//	while(en.hasMoreElements()) {
//		
//		String name = en.nextElement();
//		log.info("Attribute : {} ", name); //SPRING_SECURITY_CONTEXT

//	}
		
//	Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//	
		
	SecurityContext context = SecurityContextHolder.getContext();	
	
	Authentication b = context.getAuthentication();
	
			
	log.info("============ GetName: {}============", b.getName());
	
	log.info("============ Principal: {}============", b.getPrincipal());
	
	log.info("============ Auth {}============", b.getAuthorities());
		
		
		
		return "index";
	}

}
