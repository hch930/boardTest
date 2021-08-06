package com.hch.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hch.auth.domain.entity.MemberEntity;
import com.hch.auth.domain.role.Role;
import com.hch.auth.dto.MemberDto;
import com.hch.auth.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	private final MemberRepository memberRepository;
	
	@Transactional
	public Long register(MemberDto memberDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		
		return memberRepository.save(memberDto.toEntity()).getId();
	}
	
	//상세 정보 조회
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MemberEntity> userEntityWrapper = memberRepository.findByUsername(username);

		if (!userEntityWrapper.isPresent()) {
			throw new UsernameNotFoundException(username + "에 해당하는 사용자가 없습니다.");
		} else {
			MemberEntity userEntity = userEntityWrapper.get();

			List<GrantedAuthority> authorities = new ArrayList<>();

			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));

			return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
		}
	}
}