package com.hch.auth.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hch.auth.domain.entity.MemberEntity;
import com.hch.auth.repository.MemberRepository;

public class CustomUserDetailsService implements UserDetailsService{
	private final MemberRepository memberRepository;
	 
	   public CustomUserDetailsService(MemberRepository memberRepository) {
	      this.memberRepository = memberRepository;
	   }
	 
	   @Override
	   @Transactional
	   public UserDetails loadUserByUsername(final String username) {
	      return memberRepository.findByUsername(username)
	         .map(memberEntity -> createUser(username, memberEntity))
	         .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
	   }
	 
	   private org.springframework.security.core.userdetails.User createUser(String username, MemberEntity memberEntity) {
	      if (!memberEntity.isActivated()) {
	         throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
	      }
	      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(memberEntity.getRole().toString());
	      
	      return new org.springframework.security.core.userdetails.User(String.valueOf(memberEntity.getUsername()),
	    		  memberEntity.getPassword(),
	    		  Collections.singleton(grantedAuthority));
	   }
}
