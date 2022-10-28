package com.example.demo.controller;

import com.example.demo.dto.UserWidgetDto;
import com.example.demo.dto.WidgetDto;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.ResponseService;
import com.example.demo.service.UserService;
import com.example.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ResponseService responseService;

    //회원가입
    @PostMapping("/signup")
    public CommonResponse signup(@RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().equals(""))
            return (responseService.errorResponse(400, "email null2"));

        if (userDto.getPassword() == null || userDto.getPassword().equals(""))
            return (responseService.errorResponse(400, "password null"));

        return (userService.signup(userDto));
    }

    //로그인
    @PostMapping("/login")
    public CommonResponse login(@RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().equals(""))
            return (responseService.errorResponse(400, "email null2"));

        if (userDto.getPassword() == null || userDto.getPassword().equals(""))
            return (responseService.errorResponse(400, "password null"));

        return (userService.login(userDto));
    }

    //GET 위젯 정보
    @GetMapping("/{email}/widgets")
    public ListResponse<UserWidgetDto[]> getUserWidget(@PathVariable String email) {
        return (userService.getUserWidget(email));
    }

    //PATCH 위젯 정보
    @PatchMapping("/{email}/widgets")
    public CommonResponse patchUserWidget(@PathVariable() String email, @RequestBody() UserWidgetDto[] userWidgetDtos) {

        return (userService.patchUserWidget(email, userWidgetDtos));
    }

    //POST 위젯 정보
    @PostMapping("/widgets")
    public CommonResponse PostWidget(@RequestBody() WidgetDto[] widgetDtos) {
        return (userService.postWidget(widgetDtos));
    }
}
