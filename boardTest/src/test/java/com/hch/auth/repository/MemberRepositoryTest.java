package com.hch.auth.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.hch.auth.domain.entity.MemberEntity;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@DisplayName("Auditing 기능 + Entity 저장")
	void findUser() {
		MemberEntity memberEntity = MemberEntity.builder()
				.name("홍충현")
				.email("ruma97@naver.com")
				.username("hch930")
				.password("cndgus97")
				.build();
		
		MemberEntity save = memberRepository.save(memberEntity);
		
		assertNotNull(save.getName());
		assertNotNull(save.getEmail());
		assertNotNull(save.getUsername());
		assertNotNull(save.getPassword());
		assertNotNull(save.getCreatedDate());
		assertNotNull(save.getModifedDate());
	}
}
