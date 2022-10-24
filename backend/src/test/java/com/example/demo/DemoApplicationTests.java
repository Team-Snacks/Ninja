package com.example.demo;

import com.example.demo.dto.UserDto;
import com.example.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DemoService demoService;
	@Test
	void 로그인테스트() {
		//given
		UserDto userDto = new UserDto();
		userDto.setEmail("krkr@gmail.com");
		userDto.setPassword("1234");

		//when
		System.out.println(demoService.login(userDto).log);

		//than
	}

	@Test
	void 회원가입테스트(){
		//given
		UserDto userDto = new UserDto();
		userDto.setEmail("krkr@gmail.com");
		userDto.setPassword("1234");

		//when

		System.out.println(demoService.signup(userDto).log);
		//than
		로그인테스트();

	}
}
