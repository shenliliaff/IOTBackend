package com.up.iotbackend.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.up.iotbackend.service.IUpDeviceInfoService;
import com.up.iotbackend.service.IUpDeviceLogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * up设备信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Controller
@CrossOrigin
@RequestMapping("/up-device-info")
@Api(value = "设备信息(门禁机)",tags = {"设备信息(门禁机)"},description = "设备信息(门禁机)")
public class UpDeviceInfoController {

    @Autowired
    IUpDeviceInfoService upDeviceInfoService;

    @Autowired
    IUpDeviceLogInfoService upDeviceLogInfoService;
    private Object data;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "id", value = "编号",required = true)
    })
    @ApiOperation(value = "获取某场馆下所有设备", notes = "一个场馆下目前只有一个门禁机，之后可能会有多个类型的设备", httpMethod = "GET")
    @GetMapping("/get-devices")
    @ResponseBody
    public ResultData getLocationDevicesById(Integer id){
        StpUtil.checkLogin();
        List<Map<String, Object>> locationDevices = upDeviceInfoService.getDevicesByLocation(id);
        for(int i = 0;i < locationDevices.size();i++){
            locationDevices.get(i).put("imgUrl","https://upcloudtech.cn/resource/门禁1.png");
        }
        return ResultData.success(locationDevices);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN",required = true)
    })
    @ApiOperation(value = "获取设备状态", notes = "获取设备状态", httpMethod = "GET")
    @GetMapping("/get-device-status")
    @ResponseBody
    public ResultData getDevicesStatus(String deviceSn){
        StpUtil.checkLogin();
        try {
            Date date = new Date();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Map<String, Object>> locationDevices = upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(deviceSn);
            if(locationDevices.size() == 1)
            {
                long mileSeconds = df.parse(DateUtil.format((LocalDateTime) locationDevices.get(0).get("update_time"),"yyyy-MM-dd HH:mm:ss")).getTime()-date.getTime();
                if(Math.abs( mileSeconds )> 60000){
                    return ResultData.success("设备掉线");
                }else{
                    return ResultData.success("设备在线");
                }
            }else {
                return ResultData.success("没拿到或拿到多条设备心跳信息");
            }
        }
        catch (ParseException e) {
            return ResultData.error(408,e.getMessage());
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN号",required = true),
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "pageNum", value = "返回记录条数",required = true)
    })
    @ApiOperation(value = "获取IOT设备详情信息", notes = "获取IOT设备详情信息", httpMethod = "GET")
    @GetMapping("/get-device-detail-info")
    @ResponseBody
    public ResultData getDeviceDetailInfo(String deviceSn,Integer pageNum){
        StpUtil.checkLogin();
        if(upDeviceInfoService.getDeviceDetailById(deviceSn,pageNum).isEmpty()){
            return ResultData.success();
        }else{
            //拼接设备详情页面返回信息
            try {
                Date date = new Date();
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                List<Map<String, Object>> deviceDetailInfo = upDeviceInfoService.getDeviceDetailById(deviceSn,pageNum);
                //拿门禁机心跳中拼接的门磁状态看门是否已经打开
                List<Map<String,Object>> doorMagneticInfo = upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(deviceSn);
                for (int i = 0; i < deviceDetailInfo.size(); i++) {
                    //增加今天昨天或者日期选项
                    if(Objects.equals(DateUtil.format((LocalDateTime) deviceDetailInfo.get(i).get("update_time"),"yyyy-MM-dd"), df.format(new Date(date.getTime())))){
                        deviceDetailInfo.get(i).put("recordDate","今天");
                    }else if (Objects.equals(DateUtil.format((LocalDateTime) deviceDetailInfo.get(i).get("update_time"),"yyyy-MM-dd"), df.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000)))){
                        deviceDetailInfo.get(i).put("recordDate","昨天");
                    }else{
                        deviceDetailInfo.get(i).put("recordDate","日期");
                    }
                    //增加变红状态
                    if(deviceDetailInfo.get(i).get("device_log_description").toString().contains("失败")){
                        deviceDetailInfo.get(i).put("OpenDoorStatus",false);
                    }else{
                        deviceDetailInfo.get(i).put("OpenDoorStatus",true);
                    }
                    //门禁状态
                    if(doorMagneticInfo.isEmpty()){
                        deviceDetailInfo.get(i).put("DoorStatus",false);
                    }else{
                        String[] status=doorMagneticInfo.get(0).get("device_log_description").toString().split("-");
                        if(status[1].equals("1")){
                            deviceDetailInfo.get(i).put("DoorStatus",true);
                        }else{
                            deviceDetailInfo.get(i).put("DoorStatus",false);
                        }

                    }
                }
                return ResultData.success(deviceDetailInfo);
            }catch (Exception e)
            {
                //System.out.println(e);
                return ResultData.bind(ResultEnum.FAILURE);
            }
        }
    }

    @ApiOperation(value = "更新设备名称信息", notes = "更新设备名称信息", httpMethod = "POST")
    @PostMapping("/update-device-name-info")
    @ResponseBody
    public ResultData upDeviceNameInfo(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upDeviceInfoService.updateDeviceNameInfo(param.get("deviceSn"),param.get("deviceName"), DateTime.now());
    }

    @ApiOperation(value = "更新设备位置信息", notes = "更新设备位置信息", httpMethod = "POST")
    @PostMapping("/update-device-location-info")
    @ResponseBody
    public ResultData upDeviceLocationInfo(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upDeviceInfoService.updateDeviceLocationInfo(param.get("deviceSn"),param.get("LocationId"), DateTime.now());
    }

    @ApiOperation(value = "远程开门", notes = "远程开门", httpMethod = "POST")
    @PostMapping("/remote_open_door")
    @ResponseBody
    public ResultData upDeviceRemoteOperation(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upDeviceInfoService.updateDeviceRemoteOperation(param.get("deviceSn"), param.get("remoteOperation"), DateTime.now(),param.get("modifier"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN",required = true),
    })
    @ApiOperation(value = "获取设备状态", notes = "获取设备状态", httpMethod = "GET")
    @GetMapping("/get-pad-device-Detail")
    @ResponseBody
    public ResultData getPadDeviceDetailInfo(String deviceSn){
        //StpUtil.checkLogin();
        //调用超星班牌信息API
        //把第三方账号密码封装到map
        //https://pengshan-szwhg.chaoxing.com/third/room?sn=test123456
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("sn", deviceSn);
        String result = "";
        ResultData deviceDetailInfo = upDeviceInfoService.getDevicePadDetailByDeviceSn(deviceSn);
        // 直接丢地址 和账号密码获取第三方的token,顺便踹下错误，方便甩锅
        result = HttpUtil.get("https://pengshan-szwhg.chaoxing.com/third/room", paramsMap);
        //                    System.out.println("result.length: "+result.length());
        //用fastJson 解析一波  直接返回
        if (!result.isEmpty()&& deviceDetailInfo.getData() != null ) {
            JSONObject object = JSON.parseObject(result);
            //System.out.println("班牌"+JSON.toJSONString(object));
            HashMap deviceDetailInfoMap = JSON.parseObject(((UpDeviceInfo) deviceDetailInfo.getData()).getRemark(), HashMap.class);
            //System.out.println("bannerImages:"+ deviceDetailInfoMap.get("bannerImages"));
            object.put("logoUrl",deviceDetailInfoMap.get("logo"));
            object.put("backgroundUrl",deviceDetailInfoMap.get("bgImg"));
            object.put("rightUrl",deviceDetailInfoMap.get("selfDefine"));
            object.put("style",deviceDetailInfoMap.get("type"));
            object.put("dataSourceType",deviceDetailInfoMap.get("dataSourceType"));
            object.put("dataSource",deviceDetailInfoMap.get("dataSource"));
            object.put("versionCode",deviceDetailInfoMap.get("versionCode"));
            object.put("bannerImages",deviceDetailInfoMap.get("bannerImages"));
            object.put("updateUrl",deviceDetailInfoMap.get("updateUrl"));
            object.put("otherUrl",deviceDetailInfoMap.get("otherUrl"));
            return ResultData.success(object);
        }else{
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
    }
    @ApiOperation(value = "更新设备背景图", notes = "更新设备背景图", httpMethod = "POST")
    @PostMapping("/update-device-img-info")
    @ResponseBody
    public ResultData upDeviceImgInfo(@RequestBody Map<String,Object> param){
        StpUtil.checkLogin();
        return upDeviceInfoService.updateDevicePadDetailByDeviceSn(param.get("deviceSn").toString(),param.get("bgImg").toString(),param.get("type").toString(),param.get("logo").toString(),param.get("selfDefine").toString(),param.get("dataSourceType").toString(),param.get("dataSource").toString(),param.get("versionCode").toString(), param.get("bannerImages").toString(), param.get("otherUrl").toString(), param.get("updateUrl").toString());
    }
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN",required = true),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "otherUrl", value = "其他平台url",required = true)
    })
    @ApiOperation(value = "获取其他平台房间信息", notes = "获取其他平台房间信息", httpMethod = "GET")
    @GetMapping("/get-pad-other-room-info")
    @ResponseBody
    public ResultData getPadOtherRoomInfo(String deviceSn,String otherUrl){
        //StpUtil.checkLogin();
        //调用超星班牌信息API
        //把第三方账号密码封装到map
        //https://pengshan-szwhg.chaoxing.com/third/room?sn=test123456
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("sn", deviceSn);
        String result = "";
        // 直接丢地址 和账号密码获取第三方的token,顺便踹下错误，方便甩锅
        result = HttpUtil.get(otherUrl, paramsMap);
        //                    System.out.println("result.length: "+result.length());
        //用fastJson 解析一波  直接返回
        if (!result.isEmpty()) {
            JSONObject object = JSON.parseObject(result);
            return ResultData.success(object);
        }else{
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
    }

//    @ApiOperation(value = "test", notes = "test", httpMethod = "get")
//    @PostMapping("/test")
//    @ResponseBody
//    public long test(){
//        //Integer.reverseBytes() 2208412960
//        Integer a=0x83a1b520;
//        int b=548774275;
//        int c=0x20b5a183;
//        long d=2208412960L;
//        String h="83a1b520";
//        Integer x=1000;
//        System.out.println("x是整数值为:"+ x);
//        System.out.println("x是转换为16进制tohexstring值为:"+Integer.toHexString(x)+"   x转完之后类型为: "+ Integer.toHexString(x).getClass());
//        System.out.println(Arrays.toString(hexString2Bytes(Integer.toHexString(x))));
//
//        return 1;
//    }
}

