package com.hch.auth.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.hch.auth.dto.MemberDto;
import com.hch.auth.repository.MemberRepository;

@SpringBootTest
public class MemberServiceTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberService memberService;
	
	public static String currentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		 return user.getUsername();
	}

/*
	@Test
	public void register(){
		MemberDto memberDto = MemberDto.builder()
				.name("홍충현")
				.email("ruma97@naver.com")
				.username("hch500")
				.password("cndgus97")
				.build();
	
		memberService.register(memberDto);		
	}
*/	
	@Test
	public void loadUserByUsername() {
//		MemberDto memberDto = MemberDto.builder()
//				.name("홍충현")
//				.email("ruma97@naver.com")
//				.username("hch500")
//				.password("cndgus97")
//				.build();
//		
//		memberService.register(memberDto);
		
		memberService.loadUserByUsername("hch50000");
	}
}
