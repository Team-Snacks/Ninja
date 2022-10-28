package com.example.demo.controller;

import com.example.demo.dto.WidgetDto;
import com.example.demo.response.CommonResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.ResponseService;
import com.example.demo.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    MarketService marketService;

    @Autowired
    ResponseService responseService;

    @GetMapping("")
    public ListResponse<WidgetDto[]> getMarketWidget(){
        return marketService.getMarketWidget();
    }

    @PostMapping("")
    public CommonResponse addMarketWidget(@RequestBody() WidgetDto widgetDto){
        if (widgetDto == null || widgetDto.getName().equals(""))
            return responseService.errorResponse(400, "Invalid data");
        return marketService.addMarketWidget(widgetDto);
    }
}
