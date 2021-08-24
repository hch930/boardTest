package com.hch.auth.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hch.auth.domain.role.Role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//접근 권한을 Protected로 설정해서 접근 권한을 최소화
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_member")
public class MemberEntity extends BaseEntity{
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//이름
	@Column(length = 10, nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String email;
	
	//아이디
	@Column(length = 15, nullable = false)
	private String username;
	
	@JsonIgnore
	@Column(length = 100, nullable = false)
	private String password;
	
	@JsonIgnore
	@Column(columnDefinition = "boolean default true")
	private boolean activated;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder
	public MemberEntity(Long id, String name, String email, String username, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
