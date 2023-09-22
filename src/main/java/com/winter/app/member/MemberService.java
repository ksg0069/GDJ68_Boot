package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception{

		MemberVO loginVO = memberDAO.getMember(memberVO);
		
		if(loginVO == null) {
			return loginVO;
		}
		
		if(loginVO.getPassword().equals(memberVO.getPassword())) {
			return loginVO;
		}
		
		return null;
		
		
	}
	
	//검증메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception {
		
		boolean check =false; // error가 없다 , true : error가 있다. 검증실패
		
		//password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true;
			
			bindingResult.rejectValue("passwordCheck","memberVO.password.equalCheck");
			
		}
		
		//아이디 중복검사
		MemberVO checkVO = memberDAO.getMember(memberVO);
		
		if(checkVO != null) {
			check= true;
			bindingResult.rejectValue("username", "memberVO.username.equalCheck"); // 필드명, properties의 key
		}
		
		return check;
		
		
	}

}
