package com.winter.app.config;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.board.PostVO;
import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class SecurityLogoutAdd implements LogoutHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		log.info("=============== Logout Add {}===============", authentication);
		
//		this.useRestTemplate();
//		this.logOutForKakao(authentication);
		
//		this.logoutForKakao(response);
		this.useWebClient();
		
		
	}
	
	//web-client
	private void useWebClient() {
		
		
		
		WebClient webClient = WebClient.builder()
								.baseUrl("https://jsonplaceholder.typicode.com/")
								.build()
								;
		Mono<ResponseEntity<PostVO>> res = webClient.get()
				 .uri("posts/1")
				 .retrieve()
				 .toEntity(PostVO.class);
		
		PostVO postVO = res.block().getBody();
		
		log.info("===========webClient {} ", postVO);
			
		
								
							 	
		
		
	}
	
	//카카오계정과 함께 로그아웃
	private void logoutForKakao(HttpServletResponse response) {
		
//		RestTemplate restTemplate = new RestTemplate();
		

		StringBuffer sb = new StringBuffer();
		sb.append("https://kauth.kakao.com/oauth/logout?");
		sb.append("client_id=");
		sb.append("e44fcc1993e7412f466f243c68e8fee1");
		sb.append("&logout_redirect_uri=");
		sb.append("http://localhost:82/member/kakaoLogout");
		
		
//		ResponseEntity<String> res = restTemplate.getForEntity(sb.toString(), String.class);
		
//		String result = res.getBody();
//		
//		log.info("++++++++++++++++++++카카오계정과 함께 로그아웃 {}++++++++++++++",result);
		
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	private void logOutForKakao(Authentication authentication) {
		RestTemplate restTemplate = new RestTemplate();
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
	
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
//		headers.add("Authorization", "Bearer "+memberVO.getAccessToken());
		headers.add("Authorization", "KakaoAK 0bf1a837747f721b9a36786c577e3777");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getName());
		 
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
		
		ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
		
		String result = res.getBody();
		
		log.info("============ 로그아웃 ID {}=========", result);
		
		
	}
	
	public void useRestTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		//파라미터 Post 일 경우
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("postId", "1");
		
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params,null); //(파라미터,헤더)
		
//		결과가 하나일 댸
//		ResponseEntity<PostVO> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", PostVO.class, request); //postVo로 받아오면 Json 파싱 안해도됨 
//		
//		PostVO result = res.getBody();
		
		
		//결과가 여러개일떄
//		List<PostVO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, request);
		
		ResponseEntity<String> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments?postId=1	", String.class, request);
		
		
		log.info("================ Comments List : {}=============", res);
	}
	
	
	
	

}
