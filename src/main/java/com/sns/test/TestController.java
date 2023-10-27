package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;

@Controller
public class TestController {

	
	@Autowired
	PostMapper postMapper;
	
	//1. string  +   response body
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "hello world";
	}
	//2. map + response body -> json
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 20);
		return map;
	}
	//3. jsp 확인-> html
	//build.gradle에 두줄 추가
	//jsp폴더만들기
	//yml생성해서 prefix, suffix 추가
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	//4. db 연동 확인 -> json
	//snsapplication 에 db 설정 안보는 설정 제거
	//databaseConfig 클래스 추가
	//resources/mappers.xml
	//application.yml에 db접속 정보 추가
	//logback-spring.xml추가(쿼리 로그)
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postMapper.selectPostList();
	}
}
