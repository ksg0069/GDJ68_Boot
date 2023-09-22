package com.winter.app.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("update")
	public void setUpdate(HttpSession session, Model model)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberVO = memberService.getLogin(memberVO);
		
		MemberInfoVO memberInfoVO = new MemberInfoVO();
		memberInfoVO.setName(memberVO.getName());
		memberInfoVO.setBirth(memberVO.getBirth());
		memberInfoVO.setEmail(memberVO.getEmail());
		
		model.addAttribute("memberInfoVO", memberInfoVO);
		
		
	}
	
	@PostMapping("update")
	public void setUpdate(@Valid MemberInfoVO memberInfoVO,BindingResult bindingResult)throws Exception{
		
		

	}
	
	@GetMapping("logout")
	public String getLogout(HttpSession session)throws Exception {
		
		session.invalidate();
		return "redirect:../";
		
	}
	
	@GetMapping("login")
	public void getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
	
		
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO,HttpSession session)throws Exception{
		
	 memberVO = memberService.getLogin(memberVO);
		
	 if(memberVO != null) {
		 session.setAttribute("member", memberVO);
		 return "redirect:../";
	 }
	 
	 return "redirect:./login";
	 
	}
	
	
	
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
		
		
		log.info("photo: {}--size: {}", photo.getOriginalFilename(),photo.getSize());
		return "redirect:../";
		
	}
	
	

}
