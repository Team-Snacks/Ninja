package com.example.demo.service;

import com.example.demo.dto.WidgetDto;
import com.example.demo.entity.Widget;
import com.example.demo.repository.WidgetRepository;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.ResponseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {
    private WidgetRepository widgetRepository;
    private ResponseService responseService;

    public MarketService(WidgetRepository widgetRepository,
                         ResponseService responseService) {
        this.responseService = responseService;
        this.widgetRepository = widgetRepository;
    }


    public ListResponse<WidgetDto[]> getMarketWidget() {
        List<Widget> widgets = widgetRepository.findAll();
        WidgetDto[] widgetDtos = new WidgetDto[widgets.size()];
        for (int i = 0; i < widgets.size(); ++i) {
            WidgetDto widgetDto = new WidgetDto();
            widgetDto.setName(widgets.get(i).getName());
            widgetDtos[i] = widgetDto;
        }
        return responseService.getListResponse(widgetDtos);
    }

    public CommonResponse addMarketWidget(WidgetDto widgetDto) {
        Widget findExistWidget = widgetRepository.findByName(widgetDto.getName());
        if (findExistWidget != null)
            return responseService.errorResponse(400, "Widget is Exist");
        Widget widget = new Widget();
        widget.setName(widgetDto.getName());
        widgetRepository.save(widget);
        return responseService.getCommonResponse();
    }
}
