package com.example.demo;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserWidgetDto;
import com.example.demo.dto.WidgetDto;
import com.example.demo.entity.Widget;
import com.example.demo.repository.WidgetRepository;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.service.MarketService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private MarketService marketService;

    @Test
    void 로그인테스트() {
        //given
        UserDto userDto = new UserDto();
        userDto.setEmail("krkr@gmail.com");
        userDto.setPassword("1234");

        //when
        System.out.println(userService.login(userDto).log);

        //than
    }

    @Test
    void 회원가입테스트(String email) {
        //given
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setPassword("1234");

        //when

        System.out.println(userService.signup(userDto).log);
        //than
        로그인테스트();
    }

    @Test
    void 위젯추가테스트(String widgetName) {
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

    UserWidgetDto 유저위젯정보생성(String name, int x, int y, int w, int h) {
        UserWidgetDto userWidgetDto = new UserWidgetDto();
        userWidgetDto.setName(name);
        userWidgetDto.setX(x);
        userWidgetDto.setY(y);
        userWidgetDto.setW(w);
        userWidgetDto.setH(h);
        return userWidgetDto;
    }

    @Test
    void 유저위젯패치테스트() {
        회원가입테스트("krkr@gmail.com");
        위젯추가테스트("widget1");
        위젯추가테스트("widget2");
        위젯추가테스트("widget3");
        //given
        UserWidgetDto[] userWidgetDtos = new UserWidgetDto[3];
        userWidgetDtos[0] = 유저위젯정보생성("widget1", 1, 1, 1, 1);
        userWidgetDtos[1] = 유저위젯정보생성("widget2", 2, 2, 2, 2);
        userWidgetDtos[2] = 유저위젯정보생성("widget3", 3, 3, 3, 3);


        userService.patchUserWidget("krkr@gmail.com", userWidgetDtos);

        userWidgetDtos[0] = 유저위젯정보생성("widget1", 999, 1, 1, 1);
        userWidgetDtos[1] = 유저위젯정보생성("widget2", 2, 2, 2, 2);
        userWidgetDtos[2] = 유저위젯정보생성("widget3", 3, 3, 3, 3);

        userService.patchUserWidget("krkr@gmail.com", userWidgetDtos);
        //when

        //than
    }

    @Test
    void 유저위젯받아오기테스트() {
        유저위젯패치테스트();

        UserWidgetDto[] userWidgetDtos = userService.getUserWidget("krkr@gmail.com").dataList;
        for (UserWidgetDto x: userWidgetDtos) {
            System.out.println(x.getName() + " " + x.getX());
        }

    }

    @Test
    void 위젯생성테스트() {
        WidgetDto[] wigetTest = new WidgetDto[3];
        wigetTest[0] = 위젯정보생성("test1");
        wigetTest[1] = 위젯정보생성("test2");
        wigetTest[2] = 위젯정보생성("test3");
        userService.postWidget(wigetTest);
    }

    WidgetDto 위젯정보생성(String name) {
        WidgetDto widgetDto = new WidgetDto();
        widgetDto.setName(name);
        return widgetDto;
    }

    @Test
    void 마켓위젯데이터추가(){
        CommonResponse cm = marketService.addMarketWidget(위젯정보생성("widget1"));
        System.out.println("마켓위젯데이터추가" + cm.log);
    }

    void 마켓위젯데이터추가(String widgetName){
        CommonResponse cm = marketService.addMarketWidget(위젯정보생성(widgetName));
        System.out.println("마켓위젯데이터추가" + widgetName + " " + cm.log);
    }
    @Test
    void 마켓의위젯목록조회(){
        마켓위젯데이터추가("widget1");
        마켓위젯데이터추가("widget2");
        마켓위젯데이터추가("widget3");
        ListResponse<WidgetDto[]> cm = marketService.getMarketWidget();
        System.out.println(cm.log);
        for (WidgetDto widgetDto: cm.dataList) {
            System.out.println(widgetDto.getName());
        }
    }
}
