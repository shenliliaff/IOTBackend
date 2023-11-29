package com.up.iotbackend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.up.iotbackend.entity.UpLocationInfo;
import com.up.iotbackend.service.IUpLocationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 * up区域信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@CrossOrigin
@RequestMapping("/up-location-info")
@Api(value = "馆内教室区域信息",tags = {"馆内教室区域信息"},description = "馆内教室区域信息")
public class UpLocationInfoController {
    @Autowired
    IUpLocationInfoService upLocationInfoService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "locationId", value = "教室编号",required = true),
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "venueId", value = "场馆编号，教室编号为-1时候才需要传")
    })
    @ApiOperation(value = "根据教室id获取其下所有的设备", notes = "根据教室id获取其下所有的设备", httpMethod = "GET")
    @GetMapping("/get-devices")
    @ResponseBody
    @SaCheckLogin
    public ResultData getLocationDevicesById(Integer locationId,Integer venueId){
        StpUtil.checkLogin();
        //locationId 为 -1 表示所有location，根据VenueId拿所有的
        if(locationId == -1){
            if(venueId.toString().isEmpty()){
                return ResultData.bind(ResultEnum.PARAMS);
            }else{
                List<Map<String,Object>> allDevices=upLocationInfoService.getDevicesByVenueId(venueId);
                if(allDevices.isEmpty()){
                    return ResultData.success();
                }else{
                    try {
                        Date date = new Date();
                        for (int i = 0; i < allDevices.size(); i++) {
                            //设备表up_device_info新增远程开门状态列，心跳接口每次调用查看是否需要远程开门，开门成功失败都写log，
                            //这里需要看device_log_info的如果update_time早于DateTime.now()-接口调用频次，证明上一次接口没有刷新，超过60秒设备掉线。
                            LocalDateTime refreshTime=  (LocalDateTime)allDevices.get(i).get("update_time");
                            Date updateTime = Date.from(refreshTime.atZone(ZoneId.systemDefault()).toInstant());
                            Long seconds=DateUtil.betweenMs(new Date(date.getTime()),updateTime);
                            System.out.println(seconds);
                            if(seconds > 600000){
                                allDevices.get(i).put("isConnect","离线");
                            }else{
                                allDevices.get(i).put("isConnect","在线");
                            }
                            //https://upcloudtech.cn/resource/门禁1.png
                            //加载图片URL
                            if(allDevices.get(i).get("device_type_name").toString().equals("门禁机")){
                                allDevices.get(i).put("imageUrl","https://upcloudtech.cn/resource/门禁1.png");
                            }
                        }
                        return ResultData.success(allDevices);
                    }catch (Exception e)
                    {
                        System.out.println(e);
                        return ResultData.bind(ResultEnum.FAILURE);
                    }
                }
            }
        }else {
            if (upLocationInfoService.getLocationDevicesById(locationId).isEmpty()) {
                return ResultData.success();
            } else {
                List<Map<String,Object>> allDevicesByLocation=upLocationInfoService.getLocationDevicesById(locationId);
                try {
                    Date date = new Date();
                    for (int i = 0; i < allDevicesByLocation.size(); i++) {
                        //设备表up_device_info新增远程开门状态列，心跳接口每次调用查看是否需要远程开门，开门成功失败都写log，
                        //这里需要看device_log_info的如果update_time早于DateTime.now()-接口调用频次，证明上一次接口没有刷新，超过7秒设备掉线。
                        LocalDateTime refreshTime=  (LocalDateTime)allDevicesByLocation.get(i).get("update_time");
                        Date updateTime = Date.from(refreshTime.atZone(ZoneId.systemDefault()).toInstant());
                        Long seconds=DateUtil.betweenMs(new Date(date.getTime()),updateTime);
                        System.out.println(seconds);
                        if(seconds > 600000){
                            allDevicesByLocation.get(i).put("isConnect","离线");
                        }else{
                            allDevicesByLocation.get(i).put("isConnect","在线");
                        }
                        //https://upcloudtech.cn/resource/门禁1.png
                        //加载图片URL
                        if(allDevicesByLocation.get(i).get("device_type_name").toString().equals("门禁机")){
                            allDevicesByLocation.get(i).put("imageUrl","https://upcloudtech.cn/resource/门禁1.png");
                        }
                    }
                    return ResultData.success(allDevicesByLocation);
                }catch (Exception e)
                {
                    System.out.println(e);
                    return ResultData.bind(ResultEnum.FAILURE);
                }
            }
        }
    }
    @ApiOperation(value = "根据教室id获取其下所有的设备", notes = "根据教室id获取其下所有的设备", httpMethod = "GET")
    @GetMapping("/get-pad-devices")
    @ResponseBody
    @SaCheckLogin
    public ResultData getLocationPadDevicesById(Integer locationId,Integer venueId){
        StpUtil.checkLogin();
        //locationId 为 -1 表示所有location，根据VenueId拿所有的
        List<Map<String,Object>> feedbackInfo=new ArrayList<>();
        if(locationId == -1){
            if(venueId.toString().isEmpty()){
                return ResultData.bind(ResultEnum.PARAMS);
            }else{
                List<Map<String,Object>> allDevices=upLocationInfoService.getDevicesByVenueIdPad(venueId);
                if(allDevices.isEmpty()){
                    return ResultData.success();
                }else{
                    try {
                        for (int i = 0;i < allDevices.size();i++) {
                            //https://upcloudtech.cn/resource/门禁1.png
                            //加载图片URL
                            if(allDevices.get(i).get("device_type_id").equals("2")){
                                HashMap<String, Object> deviceInfo=new HashMap<String,Object>();
                                if (!allDevices.isEmpty()){
                                    //deviceInfo.put("deviceInfo",allDevices.get(i));
                                    deviceInfo.put("imageUrl","https://upcloudtech.cn/resource/pad-icon.png");
                                    deviceInfo.put("device_sn",allDevices.get(i).get("device_sn"));
                                    deviceInfo.put("device_name",allDevices.get(i).get("device_name"));
                                    deviceInfo.put("location_name",allDevices.get(i).get("location_name"));
                                    deviceInfo.put("device_type_id",allDevices.get(i).get("device_type_id"));
                                    deviceInfo.put("location_id",allDevices.get(i).get("location_id"));
                                    deviceInfo.put("isConnect",allDevices.get(i).get("isConnect"));
                                    feedbackInfo.add(deviceInfo);
                                }
                            }
                        }
                        return ResultData.success(feedbackInfo);
                    }catch (Exception e)
                    {
                        return ResultData.bind(ResultEnum.FAILURE);
                    }
                }
            }
        }else {
            if (upLocationInfoService.getDevicesByLocationIdPad(locationId.toString()).isEmpty()) {
                return ResultData.success();
            } else {
                List<Map<String,Object>> allDevices=upLocationInfoService.getDevicesByLocationIdPad(locationId.toString());
                if(allDevices.isEmpty()){
                    return ResultData.success();
                }else{
                    try {
                        for (int i = 0;i < allDevices.size();i++) {
                            //https://upcloudtech.cn/resource/门禁1.png
                            //加载图片URL
                            if(allDevices.get(i).get("device_type_id").equals("2")){
                                HashMap<String, Object> deviceInfo=new HashMap<String,Object>();
                                if (!allDevices.isEmpty()){
                                    deviceInfo.put("imageUrl","https://upcloudtech.cn/resource/pad-icon.png");
                                    deviceInfo.put("device_sn",allDevices.get(i).get("device_sn"));
                                    deviceInfo.put("device_name",allDevices.get(i).get("device_name"));
                                    deviceInfo.put("location_name",allDevices.get(i).get("location_name"));
                                    deviceInfo.put("device_type_id",allDevices.get(i).get("device_type_id"));
                                    deviceInfo.put("location_id",allDevices.get(i).get("location_id"));
                                    deviceInfo.put("isConnect",allDevices.get(i).get("isConnect"));
                                    feedbackInfo.add(deviceInfo);
                                }
                            }
                        }
                        return ResultData.success(feedbackInfo);
                    }catch (Exception e)
                    {
//                        System.out.println(e);
                        return ResultData.bind(ResultEnum.FAILURE);
                    }
                }
            }
        }
    }
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "venueId", value = "场馆编号",required = true)
    })
    @ApiOperation(value = "根据场馆id获取所有教室", notes = "根据场馆id获取所有教室", httpMethod = "GET")
    @GetMapping("/get-all-locations")
    @ResponseBody
    public ResultData getAllLocations(Integer venueId){
        StpUtil.checkLogin();
        if(upLocationInfoService.getAllLocations(venueId).isEmpty()){
            return ResultData.success();
        }else{
            //手动拼接一个名称叫做全部的 id为-1的 location 传给前端
            List<UpLocationInfo> allLocations =upLocationInfoService.getAllLocations(venueId);
            allLocations.add(0, new UpLocationInfo(-1,"全部","代码创建全部场馆选项"));
            return ResultData.success(allLocations);
        }
    }

    @ApiOperation(value = "增加新教室", notes = "增加新教室", httpMethod = "POST")
    @PostMapping("/add-location")
    @ResponseBody
    public ResultData addLocation(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upLocationInfoService.addLocation(Integer.parseInt(param.get("venueId")),param.get("locationName"),param.get("creator"));
    }

    @PostMapping("/update-location")
    @ResponseBody
    public ResultData updateLocation(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upLocationInfoService.updateLocation(Integer.parseInt(param.get("locationId")),param.get("locationName"),param.get("modifier"));
    }

    @PostMapping("/delete-location")
    @ResponseBody
    public ResultData deleteLocation(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upLocationInfoService.deleteLocation(Integer.parseInt(param.get("locationId")),param.get("modifier"));
    }

    @ApiOperation(value = "更新教室名称", notes = "更新教室名称", httpMethod = "POST")
    @PostMapping("/update-location-detail-info")
    @ResponseBody
    public ResultData updateLocationDetailInfo(@RequestBody Map<String,String> param){
        //StpUtil.checkLogin();
        return upLocationInfoService.updateLocationName(Integer.parseInt(param.get("locationId")), param.get("locationName"), param.get("modifier"),DateTime.now(),param.get("area"),param.get("volume"),param.get("desc"),param.get("image"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备编号",required = true)
    })
    @ApiOperation(value = "获取教室详情", notes = "获取教室详情", httpMethod = "GET")
    @GetMapping("/get-location-detail")
    @ResponseBody
    public ResultData getLocationDetailInfo(String deviceSn){
        //StpUtil.checkLogin();
        ResultData LocationDetailInfo = upLocationInfoService.getLocationDetailInfo(deviceSn);
        if (LocationDetailInfo.getCode() == 200) {
            UpLocationInfo location = (UpLocationInfo) LocationDetailInfo.getData();
            JSONObject object = JSON.parseObject(location.getRemark());
            //System.out.println("object"+JSON.toJSONString(object));
            if(!Objects.isNull(object)) {
                object.put("name", location.getLocationName());
                return ResultData.success(object);
            }else{
                return ResultData.bind(ResultEnum.PARAMS);
            }
        }else{
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
    }
}
