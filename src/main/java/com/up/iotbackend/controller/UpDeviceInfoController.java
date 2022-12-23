package com.up.iotbackend.controller;


import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.up.iotbackend.service.IUpDeviceInfoService;
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
 * up设备信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@RequestMapping("/up-device-info")
public class UpDeviceInfoController {

    @Autowired
    IUpDeviceInfoService upDeviceInfoService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "id", value = "编号")
    })
    @ApiOperation(value = "getDevices", notes = "getDevices", httpMethod = "GET")
    @GetMapping("/get-devices")
    @ResponseBody
    public ResultData getLocationDevicesById(Integer id){
        return upDeviceInfoService.getDevicesByLocation(id);
    }
}
