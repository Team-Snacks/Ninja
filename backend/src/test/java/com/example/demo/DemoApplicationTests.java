package com.example.demo;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserWidgetDto;
import com.example.demo.entity.Widget;
import com.example.demo.repository.WidgetRepository;
import com.example.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DemoService demoService;
	@Autowired
	private WidgetRepository widgetRepository;
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
	void 회원가입테스트(String email){
		//given
		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setPassword("1234");

		//when

		System.out.println(demoService.signup(userDto).log);
		//than
		로그인테스트();
	}

	@Test
	void 위젯추가테스트(String widgetName){
		//given
		Widget widget = new Widget();
        widget.setName(widgetName);
        Widget widget1 = widgetRepository.save(widget);
		if (widget1 == null)
			System.out.println("null");
		else
			System.out.println("Widget Add OK");
		//when

		//than
	}

	@Test
	void 유저위젯패치테스트(){
		회원가입테스트("krkr@gmail.com");
		위젯추가테스트("widget1");
		위젯추가테스트("widget2");
		//given
		UserWidgetDto[] userWidgetDtos = new UserWidgetDto[2];
		UserWidgetDto userWidgetDto = new UserWidgetDto();
		userWidgetDto.setName("widget1");
		userWidgetDto.setX(1);
		userWidgetDto.setY(2);
		userWidgetDto.setW(3);
		userWidgetDto.setH(4);
		userWidgetDtos[0] = userWidgetDto;

		UserWidgetDto userWidgetDto2 = new UserWidgetDto();
		userWidgetDto2.setName("widget2");
		userWidgetDto2.setX(5);
		userWidgetDto2.setY(6);
		userWidgetDto2.setW(7);
		userWidgetDto2.setH(8);
		userWidgetDtos[1] = userWidgetDto2;

		demoService.patchUserWidget("krkr@gmail.com", userWidgetDtos);
		//when

		//than
	}

	@Test
	void 유저위젯받아오기테스트(){
		유저위젯패치테스트();

		UserWidgetDto[] userWidgetDtos = demoService.getUserWidget("krkr@gmail.com");
		for (UserWidgetDto x: userWidgetDtos) {
			System.out.println(x.getName());
		}
	}
}
