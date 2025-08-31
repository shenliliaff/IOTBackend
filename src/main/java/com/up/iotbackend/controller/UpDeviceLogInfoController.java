package com.up.iotbackend.controller;


import ch.qos.logback.classic.pattern.SyslogStartConverter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpDeviceLogInfo;
import com.up.iotbackend.service.IUpDeviceLogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * up设备记录信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@CrossOrigin
@RequestMapping("/up-device-log-info")
@Api(value = "设备Log记录信息",tags = {"设备Log记录信息"},description = "设备Log记录信息")
public class UpDeviceLogInfoController {
    @Autowired
    IUpDeviceLogInfoService upDeviceLogInfoService;
//    获取门禁log记录
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN号",required = true),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "pageNum", value = "返回记录条数,想要返回所有记录可以把这个值填写的很大",required = true)
    })
    @ApiOperation(value = "返回记录条数", notes = "返回记录条数", httpMethod = "GET")
    @GetMapping("/get-device-log-info")
    @ResponseBody
    public ResultData getDeviceLogInfo(String deviceSn,Integer pageNum){
        StpUtil.checkLogin();
        if(upDeviceLogInfoService.getDeviceLogByDeviceSn(deviceSn,pageNum).isEmpty()){
            return ResultData.success();
        }else{
            //拼接设备详情页面返回信息
            try {
                Date date = new Date();
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                List<Map<String, Object>> deviceLogDetailInfo = upDeviceLogInfoService.getDeviceLogByDeviceSn(deviceSn,pageNum);
                Map<String, Object> recordsInfo = new HashMap<String, Object>();
                //Map<String, Object> dateInfo = new HashMap<String, Object>();
                List<Map<String, Object>> recordList = new LinkedList();
                //List<Map<String, Object>> dateList = new LinkedList();
                List<Map<String, Object>> feedbackList = new LinkedList();
                for (int i = 0; i < deviceLogDetailInfo.size(); i++) {
                    //增加今天昨天或者日期选项
                    if(Objects.equals(DateUtil.format((LocalDateTime) deviceLogDetailInfo.get(i).get("update_time"),"yyyy-MM-dd"), df.format(new Date(date.getTime())))){
                        deviceLogDetailInfo.get(i).put("date","今天");
                    }else if (Objects.equals(DateUtil.format((LocalDateTime) deviceLogDetailInfo.get(i).get("update_time"),"yyyy-MM-dd"), df.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000)))){
                        deviceLogDetailInfo.get(i).put("date","昨天");
                    }else{
                        deviceLogDetailInfo.get(i).put("date",DateUtil.format((LocalDateTime) deviceLogDetailInfo.get(i).get("update_time"),"yyyy-MM-dd"));
                        //recordsInfo.put("recordDate",DateUtil.format((LocalDateTime) deviceLogDetailInfo.get(i).get("update_time"),"yyyy-MM-dd"));
                    }
                    //增加变红状态
                    if(deviceLogDetailInfo.get(i).get("device_log_description").toString().contains("失败")||deviceLogDetailInfo.get(i).get("device_log_description").toString().contains("非法")){
                        deviceLogDetailInfo.get(i).put("OpenDoorStatus",false);
                    }else{
                        deviceLogDetailInfo.get(i).put("OpenDoorStatus",true);
                    }
                    deviceLogDetailInfo.get(i).put("update_time",DateUtil.format((LocalDateTime) deviceLogDetailInfo.get(i).get("update_time"),"HH:mm:ss"));
                    recordList.add(deviceLogDetailInfo.get(i));
                }
                recordsInfo.put("records",recordList);
                feedbackList.add(recordsInfo);
                return ResultData.success(feedbackList);
            }catch (Exception e)
            {
                //System.out.println(e);
                return ResultData.bind(ResultEnum.FAILURE);
            }
        }
    }

    @ApiOperation(value = "更新设备名称信息", notes = "更新设备信息", httpMethod = "POST")
    @PostMapping("/update-device-log-info")
    @ResponseBody
    public ResultData upDeviceLogInfo(@RequestBody Map<String,String> param){
        return upDeviceLogInfoService.insertDeviceLogInfo(param.get("deviceSn"), param.get("log") , "彭山班牌" );
    }
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN号",required = true),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "pageNum", value = "返回记录条数,想要返回所有记录可以把这个值填写的很大",required = true)
    })
    @ApiOperation(value = "返回记录条数", notes = "返回记录条数", httpMethod = "GET")
    @GetMapping("/get-device-log-test-info")
    @ResponseBody
    public ResultData getDeviceLogTestInfo(String deviceSn,Integer pageNum){
        if(upDeviceLogInfoService.getDeviceLogByDeviceSn(deviceSn,pageNum).isEmpty()){
            return ResultData.success();
        }else{
            return ResultData.success(upDeviceLogInfoService.getDeviceLogByDeviceSn(deviceSn,pageNum));
        }
    }
}
