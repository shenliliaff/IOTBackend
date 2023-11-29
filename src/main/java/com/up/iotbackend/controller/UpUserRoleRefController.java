package com.up.iotbackend.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.service.IUpUserRoleRefService;
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
 * up用户角色关系信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@CrossOrigin
@RequestMapping("/up-user-role-ref")
@Api(value = "根据角色获取员工列表信息",tags = {"员工下拉列表"},description = "员工下拉列表")
public class UpUserRoleRefController {
    //获取所有可选的某个venueId 的sysadmin
    @Autowired
    IUpUserRoleRefService upUserRoleRefService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "venueId", value = "场馆编号",required = true),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "roleName", value = "角色名称，填sys_admin",required = true)
    })
    @ApiOperation(value = "根据角色名称获取某场馆下所有管理员", notes = "根据角色名称获取某场馆下所有管理员", httpMethod = "GET")
    @GetMapping("/get-users-by-roleName")
    @ResponseBody
    public ResultData getAllUsersByRoleName(Integer venueId, String roleName){
        StpUtil.checkLogin();
        if(upUserRoleRefService.getAllUsersByRoleName(venueId,roleName).isEmpty())
        {
            return ResultData.success();
        }else {
            List<Map<String,Object>> userRoles = upUserRoleRefService.getAllUsersByRoleName(venueId,roleName);
            return ResultData.success(userRoles);
        }
    }
}
