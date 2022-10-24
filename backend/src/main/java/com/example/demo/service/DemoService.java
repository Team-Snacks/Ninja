package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.WidgetDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Vector;

@Transactional
@Service
public class DemoService {
    private UserRepository userRepository;
    //    private UserWidgetRepository userWidgetRepository;
//    private WidgetRepository widgetRepository;
//
    private ResponseService responseService;

    public DemoService(UserRepository userRepository, ResponseService responseService){
        this.userRepository = userRepository;
        this.responseService = responseService;
    }

    public CommonResponse signup(UserDto userDto) {
        String dtoEmail = userDto.getEmail();
        UserEntity findExistUser = userRepository.findByName(dtoEmail);
        if (findExistUser != null)
            return responseService.errorResponse(400, "User already Exist");

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
        return responseService.getCommonResponse();
    }

    public CommonResponse login(UserDto userDto) {
        String dtoEmail = userDto.getEmail();
        String dtoPassword = userDto.getPassword();
        UserEntity findExistUser = userRepository.findByName(dtoEmail);
        if (findExistUser == null)
            return responseService.errorResponse(400, "User not found");
        // System.out.println(findExistUser.getPassword() + " password");
        if (dtoPassword.equals(findExistUser.getPassword()))
            return responseService.getCommonResponse();
        return responseService.errorResponse(400, "Login Error");
    }

//    public boolean patchUserWidget(String email, WidgetDto[] widgetDtos) {
//        UserEntity findExistUser = userRepository.findByValue(email);
//        if (findExistUser == null)
//            return false;
//
//        for (WidgetDto widgetDto : widgetDtos) {
//            WidgetEntity findExistWidget = widgetRepository.findByValue(widgetDto.getName());
//            if (findExistWidget == null)
//                return false;
//            UserWidgetEntity userWidgetEntity = new UserWidgetEntity();
//            userWidgetEntity.setUserId(findExistUser.getId());
//            userWidgetEntity.setWidgetId(findExistWidget.getId());
//            userWidgetEntity.setX(widgetDto.getX());
//            userWidgetEntity.setY(widgetDto.getY());
//            userWidgetEntity.setW(widgetDto.getW());
//            userWidgetEntity.setH(widgetDto.getH());
//            userWidgetRepository.save(userWidgetEntity);
//        }
//        return true;
//    }

//    public WidgetDto[] getUserWidget(String email) {
//        UserEntity findExistUser = userRepository.findByValue(email);
//        if (findExistUser == null)
//            return null;
//
//        UserWidgetEntity[] userWidgetEntities = userWidgetRepository.findById(findExistUser.getId());
//        if (userWidgetEntities == null)
//            return null;
//        Vector<WidgetDto> vec = new Vector<>();
//        for (UserWidgetEntity entity : userWidgetEntities) {
//            WidgetDto widgetDto = new WidgetDto();
//            WidgetEntity widgetEntity = widgetRepository.findById(entity.getWidgetId());
//            if (widgetEntity == null)
//                return null;
//            widgetDto.setName(widgetEntity.getName());
//            widgetDto.setX(entity.getX());
//            widgetDto.setY(entity.getY());
//            widgetDto.setW(entity.getW());
//            widgetDto.setH(entity.getH());
//            vec.add(widgetDto);
//        }
//        WidgetDto[] widgetDtos = new WidgetDto[vec.size()];
//        for (int i = 0; i < vec.size(); i++) {
//            widgetDtos[i] = vec.get(i);
//        }
//        return widgetDtos;
//    }
}
