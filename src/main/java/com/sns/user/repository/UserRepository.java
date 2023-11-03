package com.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	//아이디 중복확인
	public UserEntity findByLoginId(String loginId);
	
	
	//로그인
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
}