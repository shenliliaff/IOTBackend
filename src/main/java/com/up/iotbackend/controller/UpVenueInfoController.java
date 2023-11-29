package com.up.iotbackend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.*;
import com.up.iotbackend.service.IUpVenueInfoService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
@CrossOrigin
@RequestMapping("/up-venue-info")
@Api(value = "场馆信息",tags = {"场馆信息"},description = "场馆信息")
public class UpVenueInfoController {

    @Autowired
    IUpVenueInfoService upVenueInfoService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "venueId", value = "场馆编号",required = true)
    })
    @ApiOperation(value = "根据场馆id获取所有设备", notes = "根据场馆id获取所有设备", httpMethod = "GET")
    @GetMapping("/get-locations-devices")
    @ResponseBody
    @SaCheckLogin
    public ResultData getLocationDevicesById(Integer venueId){
        StpUtil.checkLogin();
        if(upVenueInfoService.getVenueLocationDevicesById(venueId).isEmpty())
        {
            return ResultData.success();
        }else {
            List<Map<String,Object>> devices = upVenueInfoService.getVenueLocationDevicesById(venueId);
            return ResultData.success(devices);
        }
    }

    @ApiOperation(value = "获取所有场馆", notes = "获取所有场馆", httpMethod = "GET")
    @GetMapping("/get-all-venues")
    @ResponseBody
    public ResultData getAllVenues(){
        StpUtil.checkLogin();
        if(upVenueInfoService.getAllVenues().isEmpty()){
            return ResultData.success();
        }else{
            List<Map<String,Object>> allVenues = upVenueInfoService.getAllVenues();
            return ResultData.success(allVenues);
        }
    }
}
