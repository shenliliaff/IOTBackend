package com.up.iotbackend.controller;


import com.up.iotbackend.entity.*;
import com.up.iotbackend.service.IUpVenueInfoService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * up场馆信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@RequestMapping("/up-venue-info")
public class UpVenueInfoController {

    @Autowired
    IUpVenueInfoService upVenueInfoService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "venueId", value = "场馆编号")
    })
    @ApiOperation(value = "getVenueLocationDevices", notes = "getVenueLocationDevices", httpMethod = "GET")
    @GetMapping("/get-locations-devices")
    @ResponseBody
    public ResultData getLocationDevicesById(Integer venueId){
        if(upVenueInfoService.getVenueLocationDevicesById(venueId).isEmpty())
        {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }else {
            return ResultData.success(upVenueInfoService.getVenueLocationDevicesById(venueId));
        }
    }

    @ApiOperation(value = "getAllVenues", notes = "getAllVenues", httpMethod = "GET")
    @GetMapping("/get-all-venues")
    @ResponseBody
    public ResultData getAllVenues(){
        if(upVenueInfoService.getAllVenues().isEmpty()){
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }else{
            return ResultData.success(upVenueInfoService.getAllVenues());
        }
    }
}
