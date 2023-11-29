package com.up.iotbackend.controller;
import cn.hutool.http.HttpUtil;
import com.up.iotbackend.util.WeChatUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.service.IUpDeviceAdminRefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/upNotification")
@Api(value = "微信消息推送认证",tags = {"微信消息推送认证"},description = "微信消息推送认证")
public class UpNotificationController {
    // 重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "signature", value = "签名"),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "timestamp", value = "时间戳"),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "nonce", value = "随机数"),
            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "echostr", value = "随机字符串")
    })
    @ApiOperation(value = "获取设备管理员", notes = "获取设备管理员", httpMethod = "GET")
    @GetMapping(value = "/notificationAuth")
    @ResponseBody
    public String notificationAuth(String signature,String timestamp,String nonce,String echostr){
        if(WeChatUtil.authNotification(signature,timestamp,nonce)){
            return echostr;
        }else{
            return null;
        }
    }
    // 推送本平台内的用户审核信息接口2023.5.7
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "openId", value = "wechat open id"),
//            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "auditThing", value = "审核事物"),
//            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "auditResult", value = "审核结果"),
//            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "rejectReason", value = "驳回原因"),
//            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "phone", value = "手机号代表用户"),
//            @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "reminder", value = "温馨提示"),
//    })
//    @ApiOperation(value = "推送用户信息", notes = "推送用户信息", httpMethod = "POST")
//    @GetMapping(value = "/pushNotification")
//    @ResponseBody
//    public String pushNotification(String openId,String auditThing,String auditResult,String rejectReason,String phone,String rejectReareminderson){
//        //如果库里没有存openId及token这个token不是access token 那么就证明他不是管理员或者没有登录过小程序，需要添加管理员并登陆小程序后才可以推送
//        if(WeChatUtil.authNotification(signature,timestamp,nonce)){
//            return echostr;
//        }else{
//            return null;
//        }
//    }
}
