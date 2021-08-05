package com.hch.auth.dto;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.hch.auth.domain.entity.MemberEntity;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto{
	private Long id;
	
	//이름
	@NotNull
	@Size(min = 1, max = 10)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Email
	private String email;
	
	//아이디
	@NotNull
	@Size(min = 1, max = 15)
	private String username;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	// DTO -> Entity
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.id(id)
				.name(name)
				.email(email)
				.username(username)
				.password(password)
				.build();
	}

	@Builder
	public MemberDto(Long id, String name, String email, String username, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
