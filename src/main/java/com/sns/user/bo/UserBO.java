package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	//댓글뿌리기
	private UserEntity getUserEntityById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	//아이디 중복확인
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	//회원가입 submit
	public Integer addUser(String loginId, String password, String name, String email) {
		// save
		UserEntity userEntity = userRepository
				.save(UserEntity.builder()
						.loginId(loginId)
						.password(password)
						.name(name)
						.email(email)
						.build());
		return userEntity == null ? null : userEntity.getId();
	}

	//로그인 
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
}