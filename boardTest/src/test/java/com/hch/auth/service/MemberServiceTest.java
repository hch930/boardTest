package com.hch.auth.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	@Test
	public void loadUserByUsername(){
		MemberDto memberDto = MemberDto.builder()
				.name("홍충현")
				.email("ruma97@naver.com")
				.username("hch500")
				.password("cndgus97")
				.build();
		
		memberService.register(memberDto);
		if(currentUserName().equals(memberDto.getUsername())) {
			System.out.println("아이디가 있습니다.");
		}else {
			memberService.loadUserByUsername(memberDto.getUsername());
		}
		
//		MemberEntity save = memberRepository.save(memberEntity);
//		
//		assertNotNull(save.getName());
//		assertNotNull(save.getEmail());
//		assertNotNull(save.getUsername());
//		assertNotNull(save.getPassword());
//		assertNotNull(save.getCreatedDate());
		
		
	}
}
