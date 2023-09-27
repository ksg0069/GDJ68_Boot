package com.winter.app.member;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("info")
	public void getInfo()throws Exception{
	 //1. DB에서 사용자 정보를 조회 해서 JSP로 보냄 (주로 사용)
	 // 2. security 세션에서 꺼내서 조회 가능 
     //세션에는 수정전에 내용이 있다, 수정되면 시큐리티 세션에 또 적용을 해줘야함
	
		
	}
	
//	@GetMapping("update")   db에서 꺼내기
//	public void setUpdate(HttpSession session, Model model)throws Exception{
//		MemberVO memberVO = (MemberVO)session.getAttribute("member");
////		memberVO = memberService.getLogin(memberVO);
//		
//		MemberInfoVO memberInfoVO = new MemberInfoVO();
//		memberInfoVO.setName(memberVO.getName());
//		memberInfoVO.setBirth(memberVO.getBirth());
//		memberInfoVO.setEmail(memberVO.getEmail());
//		
//		model.addAttribute("memberInfoVO", memberInfoVO);
//		
//		
//	}
	
	@GetMapping("update") //시큐리티 세션에서 꺼내기
	public void setUpdate(@AuthenticationPrincipal MemberVO memberVO , Model model)throws Exception{
		

		

		model.addAttribute("memberInfoVO", memberVO);
		
		
	}
	
	@PostMapping("update")
	public String setUpdate(@Valid MemberInfoVO memberInfoVO,BindingResult bindingResult)throws Exception{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO memberVO = (MemberVO) obj;
		memberVO.setEmail("ksg@gg.com");
		
		return "redirect:/";

	}
	
	@GetMapping("logout")
	public String getLogout(HttpSession session)throws Exception {
		
		session.invalidate();
		return "redirect:../";
		
	}
	
	@GetMapping("login")  
	public String getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
	
		SecurityContext context =  SecurityContextHolder.getContext();
		String check = context.getAuthentication().getPrincipal().toString();
		
		log.info("======= Name {} ======",check);
		
		//로그인 후뒤로가기하거나 url쳐도 다시로그인폼으로 안가기 위해
		if(!check.equals("anonymousUser")) {
			return "redirect:/";
		}
		
		return "member/login";
		
		
	}

//	@PostMapping("login") //security 사용해서 주석
//	public String getLogin(MemberVO memberVO,HttpSession session)throws Exception{
//		
//	 memberVO = memberService.getLogin(memberVO);
//		
//	 if(memberVO != null) {
//		 session.setAttribute("member", memberVO);
//		 return "redirect:../";
//	 }
//	 
//	 return "redirect:./login";
//	 
//	}
	
	
	
//	@GetMapping("join")
//	public void setJoin(Model model)throws Exception{
//		
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("member",memberVO);
//		
//	}
	
	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
		
	}
	
	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile photo)throws Exception{ //bingresult는 memverVO 바로뒤에 있어야함 순서중요
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		if(bindingResult.hasErrors() || check) {
			return "member/join";
		}
		
		//회원가입 진행
		
		int result  = memberService.setJoin(memberVO);
		
		
		log.info("photo: {}--size: {}", photo.getOriginalFilename(),photo.getSize());
		return "redirect:../";
		
	}
	
	

}
