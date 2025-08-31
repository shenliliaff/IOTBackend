package com.up.iotbackend.controller;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.mapper.UpDeviceAdminRefMapper;
import com.up.iotbackend.service.IUpDeviceAdminRefService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备管理员关系信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
    @Controller
    @CrossOrigin
    @RequestMapping("/up-device-admin-ref")
    @Api(value = "设备管理员信息",tags = {"设备管理员信息"},description = "设备管理员信息")
    public class UpDeviceAdminRefController {
        @Autowired
        IUpDeviceAdminRefService upDeviceAdminRefService;
        @ApiImplicitParams({
                @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN号",required = true)
        })
        @ApiOperation(value = "获取设备管理员", notes = "获取设备管理员", httpMethod = "GET")
        @GetMapping("/get-device-admins")
        @ResponseBody
        public ResultData getDeviceAdmins(String deviceSn){
            StpUtil.checkLogin();
            if(upDeviceAdminRefService.getDeviceAdmins(deviceSn).isEmpty()){
                return ResultData.success();
            }else{
                List<Map<String,Object>> deviceAdmins = upDeviceAdminRefService.getDeviceAdmins(deviceSn);
                return ResultData.success(deviceAdmins);
            }
        }

        @ApiOperation(value = "增加设备管理员", notes = "增加设备管理员", httpMethod = "POST")
        @PostMapping("/add-device-admins")
        @ResponseBody
        public ResultData addDeviceAdmin(@RequestBody Map<String,String> param){
            return upDeviceAdminRefService.addDeviceAdmin(param.get("deviceSn"),param.get("userId"),param.get("creator"));
        }
    }
