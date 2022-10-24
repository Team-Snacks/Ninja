package com.example.demo.controller;

import com.example.demo.dto.WidgetDto;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.ResponseService;
import com.example.demo.service.DemoService;
import com.example.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class DemoController {
    @Autowired
    DemoService demoService;

    @Autowired
    ResponseService responseService;

    //회원가입
    @PostMapping("")
    public CommonResponse signup(@RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().equals(""))
            return (responseService.errorResponse(400, "email null"));

        if (userDto.getPassword() == null || userDto.getPassword().equals(""))
            return (responseService.errorResponse(400, "password null"));

        return (demoService.signup(userDto));
    }

    //로그인
    @PostMapping("/login")
    public CommonResponse login(@RequestBody UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().equals(""))
            return (responseService.errorResponse(400, "email null"));

        if (userDto.getPassword() == null || userDto.getPassword().equals(""))
            return (responseService.errorResponse(400, "password null"));

        return (demoService.login(userDto));
    }

    //GET 위젯 정보
//    @GetMapping("/{email}/widget")
//    public ListResponse getUserWidget(@PathVariable String email) {
//        List<WidgetDto> widgetDto = demoService.getUserWidget(email);
//        return (responseService.getListResponse(widgetDto));
//    }
//
//    //PATCH 위젯 정보
//    @PatchMapping("/{email}/widget")
//    public CommonResponse patchUserWidget(@PathVariable String email, @RequestBody WidgetDto[] widgetDto) {
//        Boolean check = demoService.patchUserWidget(email, widgetDto);
//        if (check == false)
//            return (responseService.errorResponse(400, "email이 유효하지 않습니다"));
//
//        return (responseService.getCommonResponse());
//    }
}
