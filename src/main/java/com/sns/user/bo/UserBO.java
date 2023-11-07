package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	//댓글 뿌릴 때 id로만 조회하는게 필요해서 만든 메소드
	public UserEntity getUserEntityById(int userId) {
		return userRepository.findById(userId).orElse(null); 
		//내가 리턴하고싶은건 UserEntity인데 리턴 되고있는건 optional(null체크가 가능한 객체)라서
		//타입이 미스매치라고 뜨기 때문에 잇으면 리턴하고 없으면 null로 리턴한다는 orElse(null)을 붙여준다
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