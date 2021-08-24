package com.hch.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hch.auth.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	@EntityGraph(attributePaths = "authorities")
	Optional<MemberEntity> findByUsername(String username);
}
