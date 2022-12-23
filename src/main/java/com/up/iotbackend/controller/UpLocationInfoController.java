package com.up.iotbackend.controller;


import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.service.IUpLocationInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * up区域信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@RequestMapping("/up-location-info")
public class UpLocationInfoController {
    @Autowired
    IUpLocationInfoService upLocationInfoService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "locationId", value = "场馆编号")
    })
    @ApiOperation(value = "getLocationDevicesById", notes = "getLocationDevicesById", httpMethod = "GET")
    @GetMapping("/get-locations-devices")
    @ResponseBody
    public ResultData getLocationDevicesById(Integer locationId){
        if(upLocationInfoService.getLocationDevicesById(locationId).isEmpty())
        {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }else {
            return ResultData.success(upLocationInfoService.getLocationDevicesById(locationId));
        }
    }

    @ApiOperation(value = "getAllLocations", notes = "getAllLocations", httpMethod = "GET")
    @GetMapping("/get-all-locations")
    @ResponseBody
    public ResultData getAllLocations(){
        if(upLocationInfoService.getAllLocations().isEmpty()){
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }else{
            return ResultData.success(upLocationInfoService.getAllLocations());
        }
    }
}
