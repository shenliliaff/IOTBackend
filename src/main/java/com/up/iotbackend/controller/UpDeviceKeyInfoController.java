package com.up.iotbackend.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpUser;
import com.up.iotbackend.service.IUpDeviceInfoService;
import com.up.iotbackend.service.IUpDeviceKeyInfoService;
import com.up.iotbackend.service.IUpDeviceLogInfoService;
import com.up.iotbackend.service.IUpUserService;
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

/**
 * <p>
 * up区域信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-24
 */
@Controller
@CrossOrigin
@RequestMapping("/up-device-key-info")
@Api(value = "门禁钥匙信息",tags = {"门禁钥匙信息"},description = "门禁钥匙信息")
public class UpDeviceKeyInfoController {
    @Autowired
    IUpDeviceKeyInfoService upDeviceKeyInfoService;
    @Autowired
    IUpDeviceLogInfoService upDeviceLogInfoService;
    @Autowired
    IUpDeviceInfoService upDeviceInfoService;
    @Autowired
    IUpUserService upUserService;
    //获取钥匙列表
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "deviceSn", value = "设备SN号",required = true)
    })
    @ApiOperation(value = "获取钥匙列表", notes = "获取钥匙列表", httpMethod = "GET")
    @GetMapping("/get-device-key-info")
    @ResponseBody
    public ResultData getDeviceKeyInfo(String deviceSn){
        StpUtil.checkLogin();
        if(upDeviceKeyInfoService.getDeviceKeyByDeviceSn(deviceSn).isEmpty()){
            return ResultData.success();
        }else{
            List<Map<String,Object>> deviceKeys = upDeviceKeyInfoService.getDeviceKeyByDeviceSn(deviceSn);
            for (Map<String, Object> deviceKey : deviceKeys) {
                deviceKey.put("start_time", DateUtil.format((LocalDateTime) deviceKey.get("start_time"), "yyyy-MM-dd HH:mm:ss"));
                deviceKey.put("end_time", DateUtil.format((LocalDateTime) deviceKey.get("end_time"), "yyyy-MM-dd HH:mm:ss"));
            }
            return ResultData.success(deviceKeys);
        }
    }
    //创建钥匙详情
    @ApiOperation(value = "创建钥匙详情", notes = "更新钥匙详情", httpMethod = "POST")
    @PostMapping("/create-device-key-info")
    @ResponseBody
    public ResultData insertDeviceKeyInfo(@RequestBody Map<String,String> param){
        //System.out.println(param.get("deviceSn"));
        StpUtil.checkLogin();
        return upDeviceKeyInfoService.insertDeviceKeyInfo(param.get("deviceSn"),param.get("phone"), param.get("owner"), param.get("startTime"), param.get("endTime"),param.get("creator"),param.get("creator"));
    }
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataTypeClass = String.class, name = "deviceKeyId", value = "设备记录编号",required = true),
            @ApiImplicitParam(paramType = "body", dataTypeClass = String.class, name = "modifier", value = "记录更新者"),
    })
    @ApiOperation(value = "更新钥匙详情", notes = "更新钥匙详情", httpMethod = "POST")
    @PostMapping("/update-device-key-info")
    @ResponseBody
    public ResultData updateDeviceKeyInfo(@RequestBody Map<String,String> param){
        //System.out.println(param.get("phone"));
        StpUtil.checkLogin();
        return upDeviceKeyInfoService.updateDeviceKeyInfo(Integer.parseInt(param.get("deviceKeyId")),param.get("phone"), param.get("owner"), param.get("startTime"), param.get("endTime"), DateTime.now(),param.get("modifier"));
    }

    @ApiOperation(value = "删除钥匙", notes = "删除钥匙", httpMethod = "POST")
    @PostMapping("/delete-device-key")
    @ResponseBody
    public ResultData deleteDeviceKey(@RequestBody Map<String,String> param){
        StpUtil.checkLogin();
        return upDeviceKeyInfoService.deleteDeviceKey(Integer.parseInt(param.get("deviceKeyId")),DateTime.now(),param.get("modifier"));
    }
    //获取钥匙列表
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "deviceKeyId", value = "钥匙主键",required = true)
    })
    @ApiOperation(value = "获取钥匙分享信息", notes = "获取钥匙分享信息", httpMethod = "GET")
    @GetMapping("/get-device-key-share-info")
    @ResponseBody
    public ResultData getDeviceKeyShareInfo(Integer deviceKeyId){
        StpUtil.checkLogin();
        if(upDeviceKeyInfoService.getDeviceKeyShareInfo(deviceKeyId).isEmpty()){
            return ResultData.success();
        }else{
            List<Map<String,Object>> deviceKeys = upDeviceKeyInfoService.getDeviceKeyShareInfo(deviceKeyId);
            return ResultData.success(deviceKeys);
        }
    }

    @ApiOperation(value = "checkCode", notes = "checkCode", httpMethod = "POST")
    @PostMapping("/CheckCode")
    @ResponseBody
    public String checkCode(@RequestBody Map<String,Object> iotRequestBody){
        try{
            //正常打开up_device_key_info校验预约信息即可
            //checkCode无论成功失败写一条log
            //获取所有预约信息,超星推送
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String[] codeVal=iotRequestBody.get("CodeVal").toString().split("-");
            String codeType=iotRequestBody.get("CodeType").toString();
            String isOnLine=iotRequestBody.get("IsOnline").toString();
            //System.out.println(iotRequestBody.get("CodeVal").toString().split("&")[1]);
            //解析CodeVal拿到校验信息 一个字符串就行 requestType-deviceKeyId-user 例如 1-af-up
            //124-56-102c2a883daf52210c3cd83ded0ac189-chaoXingXiangShan
            if(codeVal[0].equals("allPass")){
                //2025.9.1 万能码是allPass-up 这个字段,象山不想要万能码，getDeviceVenueInfoById
                Map<String, Object> deviceVenueInfo = upDeviceInfoService.getDeviceVenueInfoById(iotRequestBody.get("SN").toString());
                if(deviceVenueInfo.get("venue_id").toString().equals("1")){
                    upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "万能码开门失败，象山不允许使用万能码","admin");
                    return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                }else {
                    upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "万能码开门成功", "admin");
                    return "{\"Status\": 1, \"StatusDesc\": \"allow Pass\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                }
            }else {
                if (codeType.equals("Q") && iotRequestBody.get("CodeVal").toString().contains("-") && codeVal.length > 2) {
                    //up请求自己校验
                    if (codeVal[codeVal.length - 1].equals("up")) {
                        //万能码随便开
                        //查询钥匙信息
                        Map<String, Object> deviceKeyInfo = upDeviceKeyInfoService.getDeviceKeyByDeviceKeyId(Integer.parseInt(codeVal[0]));
                        //是同一用户且开始时间早于now 且 结束时间晚于now
                        //System.out.println("up 判断条件 ");
                        //System.out.println(df.parse(deviceKeyInfo.get("start_time").toString()).before(DateTime.now()) & df.parse(deviceKeyInfo.get("end_time").toString()).after(DateTime.now())
                        //& deviceKeyInfo.get("owner").toString().equals(codeVal[1]) & deviceKeyInfo.get("device_sn").toString().equals(iotRequestBody.get("SN").toString()));
                        if (df.parse(deviceKeyInfo.get("start_time").toString()).before(DateTime.now()) & df.parse(deviceKeyInfo.get("end_time").toString()).after(DateTime.now())
                                & deviceKeyInfo.get("phone").toString().equals(codeVal[1]) & deviceKeyInfo.get("device_sn").toString().equals(iotRequestBody.get("SN").toString())) {
                            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "用户" + codeVal[1] + "开门成功", codeVal[1]);
                            return "{\"Status\": 1, \"StatusDesc\": \"allow Pass\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                        } else {
                            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "用户" + codeVal[1] + "开门失败", codeVal[1]);
                            return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                        }
                    } else if (codeVal[codeVal.length - 1].equals("chaoXingBeiLun")) {
                        //调用超星北仑扫码校验API
                        //把第三方账号密码封装到map
                        //https://beilunqu.chaoxing.com/api/room/CheckCodeNew?CodeVal=133-57-6b9060e99343fa3d629bdf425a4e5998-135****8363-chaoXingBeiLun&SN=2204419779&ak=08d398cbc62ec5fd917ab65ed08a396c57ad3317&sk=2ae245311974e7a24a9af5ee5704bd1c
                        Map<String, Object> paramsMap = new HashMap<String, Object>();
                        paramsMap.put("ak", "08d398cbc62ec5fd917ab65ed08a396c57ad3317");
                        paramsMap.put("sk", "2ae245311974e7a24a9af5ee5704bd1c");
                        paramsMap.put("SN", iotRequestBody.get("SN").toString());
                        paramsMap.put("CodeVal", iotRequestBody.get("CodeVal").toString());
                        String result = "";
                        // 直接丢地址 和账号密码获取第三方的token,顺便踹下错误，方便甩锅
                        result = HttpUtil.get("https://beilunqu.chaoxing.com/api/room/CheckCodeNew", paramsMap);
                        //                    System.out.println("result.length: "+result.length());
                        //用fastJson 解析一波  直接返回
                        if (!result.isEmpty()) {
                            JSONObject object = JSON.parseObject(result);
                            //                        System.out.println("北仑"+JSON.toJSONString(object));
                            String status = object.getString("Status");
                            //                        System.out.println("Status:"+status);
                            //String StatusDesc = object.getString("StatusDesc");
                            //                        System.out.println(StatusDesc);
                            //JSONObject token = JSON.parseObject(CodeValObj);
                            //System.out.println("token-->"+token.getString("token"));
                            //return token.getString("token");
                            if (status.equals("1")) {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3] + "开门成功", "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3]);
                                return "{\"Status\": 1, \"StatusDesc\": \"allow Pass\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            } else if (status.equals("0")) {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3] + "开门失败", "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3]);
                                return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            } else {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                                return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            }
                        } else {
                            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                            return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                        }
                    } else if (codeVal[codeVal.length - 1].equals("chaoXingXiangShan")) {
                        //调用超星象山扫码校验API
                        //把第三方账号密码封装到map
                        Map<String, Object> paramsMap = new HashMap<String, Object>();
                        paramsMap.put("ak", "08d398cbc62ec5fd917ab65ed08a396c57ad3317");
                        paramsMap.put("sk", "2ae245311974e7a24a9af5ee5704bd1c");
                        paramsMap.put("SN", iotRequestBody.get("SN").toString());
                        paramsMap.put("CodeVal", iotRequestBody.get("CodeVal").toString());
                        String result = "";
                        // 直接丢地址 和账号密码获取第三方的token,顺便踹下错误，方便甩锅
                        result = HttpUtil.get("https://xiangshan-szwhg.chaoxing.com/api/room/CheckCodeNew", paramsMap);
                        //用fastJson 解析一波  直接返回
                        if (!result.isEmpty()) {
                            JSONObject object = JSON.parseObject(result);
                            //                        System.out.println("象山:" + JSON.toJSONString(object));
                            String status = object.getString("Status");
                            //                        System.out.println(status);
                            //String StatusDesc = object.getString("StatusDesc");
                            //                        System.out.println(StatusDesc);
                            //JSONObject token = JSON.parseObject(CodeValObj);
                            //System.out.println("token-->"+token.getString("token"));
                            //return token.getString("token");
                            if (status.equals("1")) {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3] + "开门成功", "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3]);
                                return "{\"Status\": 1, \"StatusDesc\": \"allow Pass\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            } else if (status.equals("0")) {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3] + "开门失败", "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3]);
                                return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            } else {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                                return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            }
                        } else {
                            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                            return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                        }
                    }else if (codeVal[codeVal.length - 1].equals("chaoXingYinZhou")) {
                        //调用超星鄞州扫码校验API
                        //把第三方账号密码封装到map
                        Map<String, Object> paramsMap = new HashMap<String, Object>();
                        paramsMap.put("ak", "08d398cbc62ec5fd917ab65ed08a396c57ad3317");
                        //paramsMap.put("sk", "2ae245311974e7a24a9af5ee5704bd1c");
                        paramsMap.put("SN", iotRequestBody.get("SN").toString());
                        paramsMap.put("CodeVal", iotRequestBody.get("CodeVal").toString());
                        String result = "";
                        // 直接丢地址 和账号密码获取第三方的token,顺便踹下错误，方便甩锅
                        result = HttpUtil.get("https://yzqwhg-szwhg.chaoxing.com/api/room/CheckCodeNew", paramsMap);
                        //用fastJson 解析一波  直接返回
                        if (!result.isEmpty()) {
                            JSONObject object = JSON.parseObject(result);
                            //                        System.out.println("象山:" + JSON.toJSONString(object));
                            String status = object.getString("Status");
                            //                        System.out.println(status);
                            //String StatusDesc = object.getString("StatusDesc");
                            //                        System.out.println(StatusDesc);
                            //JSONObject token = JSON.parseObject(CodeValObj);
                            //System.out.println("token-->"+token.getString("token"));
                            //return token.getString("token");
                            if (status.equals("1")) {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3] + "开门成功", "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3]);
                                return "{\"Status\": 1, \"StatusDesc\": \"allow Pass\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            } else if (status.equals("0")) {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3] + "开门失败", "超星用户" + iotRequestBody.get("CodeVal").toString().split("-")[3]);
                                return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            } else {
                                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                                return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                            }
                        } else {
                            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                            return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                        }
                    } else {
                        upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), "非法请求", "admin");
                        return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                    }
                } else {
                    //刷卡走这里，卡号等于设备device_sn即可通过
                    //2023.4.5放宽限制正序反序等于device_sn均可以，北仑大玻璃门总自动重置刷卡规则为反序
                    //查询一下卡号是否为device_sn 有心跳记录证明正常在线设备可以刷开门
                    //List<Map<String, Object>> is_device_sn_exist=upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(iotRequestBody.get("CodeVal").toString());
                    //device_snz转为long，反序reverseBytes即为反序结果
                    if (codeType.equals("C") && (iotRequestBody.get("SN").toString().equals(iotRequestBody.get("CodeVal").toString()) || Integer.toString(Integer.reverseBytes((int)Long.parseLong(iotRequestBody.get("SN").toString()))).equals(iotRequestBody.get("CodeVal").toString()))) {
                        //System.out.println(iotRequestBody.get("CodeVal"));
                        upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), iotRequestBody.get("CodeVal").toString() + "刷卡开门成功", "admin");
                        return "{\"Status\": 1, \"StatusDesc\": \"allow Pass\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                    } else {
                        upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), iotRequestBody.get("CodeVal").toString() + "非法刷卡请求", "admin");
                        return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
                    }
                }
            }
        }catch (Exception e){
            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(), e.toString(), "Exception");
            return "{\"Status\": 0, \"StatusDesc\": \"No Entry\", \"Relay1Time\": 3000,\"BeepType\": 1,\"BeepTime\": 300}";
        }

    }

//    @ApiOperation(value = "IsConnect", notes = "IsConnect", httpMethod = "POST")
//    @PostMapping("/IsConnect")
//    @ResponseBody
//    //有H2 连上来让它过去不管
//    public void IsConnect(@RequestBody Map<String,Object> iotRequestBody){
//
//    }

    //设备调用 CheckCode 接口在线验证后，成功开门或通过闸机后，设备请求该接口，以告之服务器结果。
//    @ApiOperation(value = "QueryCmdPostData", notes = "QueryCmdPostData", httpMethod = "POST")
//    @PostMapping("/CheckCodeWalkPast")
//    @ResponseBody
//    public String checkCodeWalkPast(@RequestBody Map<String,Object> iotRequestBody){
//        List<Map<String,Object>> open_door=upDeviceLogInfoService.getDeviceRemoteOperation(iotRequestBody.get("SN").toString(),"open_door");
//        return "{\"CmdID\": \"123\",\"CmdCode\": 1,\"CmdStatus\":\"1\",\"CmdParams\":{}}";
//        //return "{\"SN\": \"2205410131\",\"DoorMagnetic\": \"1\"}";
//        //return "{\"CmdID\": \"123\",\"CmdCode\": 5,\"CmdParams\":{\"ViewId\":\"beilun3\",\"UID\":\"beilun4\"}}";
//    }

    //设备表up_device_info新增远程开门状态列，心跳接口每次调用查看是否需要远程开门，开门成功失败都写log，
    //如果update_time早于DateTime.now()-接口调用频次，证明上一次接口没有刷新，设备掉线。
    //CmdID可以把参数带到querycmdpostdata函数中
    @ApiOperation(value = "queryCmd", notes = "queryCmd", httpMethod = "POST")
    @PostMapping("/QueryCmd")
    @ResponseBody
    public String queryCmd(@RequestBody Map<String,Object> iotRequestBody){
        List<Map<String,Object>> open_door_list=upDeviceLogInfoService.getDeviceRemoteOperation(iotRequestBody.get("SN").toString(),"open_door");
        if(!open_door_list.isEmpty()){
            //如果正常交互，3秒调用一次，就一条一条慢慢调用
            //参数放到CmdID里面带到queryCmdPostData方法中
            //这里cmdID传中文会出错 ???所以传成了手机号
            if(open_door_list.get(0).get("device_sn").equals(iotRequestBody.get("SN").toString())){
//                System.out.println("远程开门开始调用");
//                System.out.println(open_door_list.get(0).get("modifier").toString());
                return "{\"CmdID\": \""+open_door_list.get(0).get("modifier")+"\",\"CmdCode\": 1,\"SN\":\""+iotRequestBody.get("SN").toString()+"\",\"CmdStatus\":1}";
            }else{
                //查看是否有心跳，没有心跳新增一条，有心跳持续更新log
                //System.out.println("正常心跳1");
                if(upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(iotRequestBody.get("SN").toString()).isEmpty()){
                    //log增加门磁状态-拼接
                    upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(),"心跳自动更新-"+iotRequestBody.get("DoorMagnetic").toString(),"门禁");
                }else{
                    List<Map<String,Object>> queryCmdLog=upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(iotRequestBody.get("SN").toString());
                    upDeviceLogInfoService.updateQueryCmd(Integer.parseInt(queryCmdLog.get(0).get("id").toString()),iotRequestBody.get("DoorMagnetic").toString(),DateTime.now());
                }
                return "{\"CmdID\": \"\",\"CmdCode\":0,\"SN\":\""+iotRequestBody.get("SN").toString()+"\",\"CmdStatus\":0}";
            }
        }else{
            //System.out.println("正常心跳2");
            if(upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(iotRequestBody.get("SN").toString()).isEmpty()){
                //log增加门磁状态-拼接
                upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(),"心跳自动更新-"+iotRequestBody.get("DoorMagnetic").toString(),"门禁");
            }else{
                List<Map<String,Object>> queryCmdLog=upDeviceLogInfoService.getDeviceLogQueryCmdByDeviceSn(iotRequestBody.get("SN").toString());
                upDeviceLogInfoService.updateQueryCmd(Integer.parseInt(queryCmdLog.get(0).get("id").toString()),iotRequestBody.get("DoorMagnetic").toString(),DateTime.now());
            }
            return "{\"CmdID\": \"\",\"CmdCode\":0,\"SN\":\""+iotRequestBody.get("SN").toString()+"\",\"CmdStatus\":0}";
        }
    }

    @ApiOperation(value = "QueryCmdPostData", notes = "QueryCmdPostData", httpMethod = "POST")
    @PostMapping("/QueryCmdPostData")
    @ResponseBody
    public String queryCmdPostData(@RequestBody Map<String,Object> iotRequestBody){
        //远程成功开门写log，并关闭remoteOperation
        if(iotRequestBody.get("CmdStatus").toString().equals("1")){
            //System.out.println("开门成功");
//            System.out.println(iotRequestBody.get("CmdID").toString());
            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(),"用户"+iotRequestBody.get("CmdID").toString()+"远程开门成功",iotRequestBody.get("CmdID").toString());
            upDeviceInfoService.updateDeviceRemoteOperation(iotRequestBody.get("SN").toString(),"",DateTime.now(),"平台管理员");
            return "{\"Status \":1,\"StatusDesc\":\"执行成功\"}}";
        }else {
//            System.out.println("开门失败");
            upDeviceLogInfoService.insertDeviceLogInfo(iotRequestBody.get("SN").toString(),"用户"+iotRequestBody.get("CmdID").toString()+"远程开门失败",iotRequestBody.get("CmdID").toString());
            return "{\"Status \":0,\"StatusDesc\":\"执行失败\"}}";
        }
    }


}
