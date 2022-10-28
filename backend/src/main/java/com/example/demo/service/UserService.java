package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserWidgetDto;
import com.example.demo.dto.WidgetDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserWidget;
import com.example.demo.entity.UserWidgetId;
import com.example.demo.entity.Widget;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserWidgetRepository;
import com.example.demo.repository.WidgetRepository;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.ResponseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Vector;

@Transactional
@Service
public class UserService {
    private UserRepository userRepository;
    private ResponseService responseService;
    private WidgetRepository widgetRepository;
    private UserWidgetRepository userWidgetRepository;

    public UserService(UserRepository userRepository,
                       ResponseService responseService,
                       WidgetRepository widgetRepository,
                       UserWidgetRepository userWidgetRepository) {
        this.userRepository = userRepository;
        this.responseService = responseService;
        this.widgetRepository = widgetRepository;
        this.userWidgetRepository = userWidgetRepository;
    }

    public CommonResponse signup(UserDto userDto) {
        String dtoEmail = userDto.getEmail();
        User findExistUser = userRepository.findByName(dtoEmail);
        if (findExistUser != null)
            return responseService.errorResponse(400, "User already Exist");

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return responseService.getCommonResponse();
    }

    public CommonResponse login(UserDto userDto) {
        String dtoEmail = userDto.getEmail();
        String dtoPassword = userDto.getPassword();
        User findExistUser = userRepository.findByName(dtoEmail);
        if (findExistUser == null)
            return responseService.errorResponse(400, "User not found");
        // System.out.println(findExistUser.getPassword() + " password");
        if (dtoPassword.equals(findExistUser.getPassword()))
            return responseService.getCommonResponse();
        return responseService.errorResponse(400, "Login Error");
    }

    public CommonResponse patchUserWidget(String email, UserWidgetDto[] userWidgetDtos) {
        User findExistUser = userRepository.findByName(email);
        if (findExistUser == null)
            System.out.println(responseService.errorResponse(400, "User not found").log);

        for (UserWidgetDto userWidgetDto : userWidgetDtos) {
            if (userWidgetDto.getName() == null)
                return responseService.errorResponse(400, "widget name null");

            Widget findExistWidget = widgetRepository.findByName(userWidgetDto.getName());

            if (findExistWidget == null) {
                Widget widget = new Widget();
                widget.setName(userWidgetDto.getName());
                widgetRepository.save(widget);

                findExistWidget = widgetRepository.findByName(userWidgetDto.getName());
            }

            UserWidgetId userWidgetId= new UserWidgetId(findExistUser.getId(), findExistWidget.getId());
            UserWidget userWidget = new UserWidget(userWidgetId);
            userWidget.setX(userWidgetDto.getX());
            userWidget.setY(userWidgetDto.getY());
            userWidget.setW(userWidgetDto.getW());
            userWidget.setH(userWidgetDto.getH());
            userWidgetRepository.save(userWidget);
        }
        return responseService.getCommonResponse();
    }


    public ListResponse<UserWidgetDto[]> getUserWidget(String email) {
        User findExistUser = userRepository.findByName(email);
        if (findExistUser == null)
            System.out.println(responseService.errorResponse(400, "User not found").log);

        UserWidget[] userWidgetEntities = userWidgetRepository.findWidgets(findExistUser.getId());
        if (userWidgetEntities == null)
            System.out.println(responseService.errorResponse(400, "User widget not found").log);
        Vector<UserWidgetDto> vec = new Vector<>();
        for (UserWidget entity : userWidgetEntities) {
            UserWidgetDto userWidgetDto = new UserWidgetDto();
            Widget widgetEntity = widgetRepository.findByWidgetId(entity.getWidgetId());
            if (widgetEntity == null)
                return null;
            userWidgetDto.setName(widgetEntity.getName());
            userWidgetDto.setX(entity.getX());
            userWidgetDto.setY(entity.getY());
            userWidgetDto.setW(entity.getW());
            userWidgetDto.setH(entity.getH());
            vec.add(userWidgetDto);
        }
        UserWidgetDto[] widgetDtos = new UserWidgetDto[vec.size()];
        for (int i = 0; i < vec.size(); i++) {
            widgetDtos[i] = vec.get(i);
        }
        return responseService.getListResponse(widgetDtos);
    }


    public CommonResponse postWidget(WidgetDto[] widgetDtos) {
        for (WidgetDto widgetDto : widgetDtos) {
            Widget widget = new Widget();
            if (widgetDto.getName() == null)
                return responseService.errorResponse(400, "widget name null");
            widget.setName(widgetDto.getName());
            widgetRepository.save(widget);
        }
        return responseService.getCommonResponse();
    }

}
