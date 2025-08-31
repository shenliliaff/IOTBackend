package com.up.iotbackend.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.up.iotbackend.util.WeChatUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpUser;
import com.up.iotbackend.service.IUpUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;

import java.util.*;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;
import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaUpdate;

/**
 * <p>
 * up用户信息表 前端控制器
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
@Controller
@CrossOrigin
@RequestMapping("/up-user")
@Api(value = "用户信息",tags = {"用户信息"},description = "用户信息")
public class UpUserController {
    @Autowired
    IUpUserService upUserService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "venueId", value = "场馆编号", required = true)
    })
    @ApiOperation(value = "根据场馆id获取用户", notes = "根据场馆id获取用户", httpMethod = "GET")
    @GetMapping("/get-users")
    @ResponseBody
    public ResultData getUsersByVenue(Integer venueId) {
        StpUtil.checkLogin();
        if (upUserService.getUsersByVenue(venueId).isEmpty()) {
            return ResultData.success();
        } else {
            return ResultData.success(upUserService.getUsersByVenue(venueId));
        }
    }
    //phone手机号做主键
    @PostMapping("login")
    @ResponseBody
    public ResultData user_login(@RequestBody Map<String,Object> param){
        List<UpUser> user = upUserService.getUserByPhone(param.get("phone").toString());
        if(user.isEmpty()) {
            return ResultData.bind(ResultEnum.ACCOUNT_NOT);
        }else{
            return ResultData.success();
    }
}

    /**
     * 微信用户登录详情
     */
    @PostMapping("wx/login")
    @ResponseBody
    public ResultData wx_user_login(@RequestBody Map<String,Object> param) throws Base64DecodingException {
        //获取用户手机号先获取token
        JSONObject tokenInfo = WeChatUtil.getAccessToken();
        JSONObject phoneObj = WeChatUtil.getPhone(tokenInfo.getObj("access_token").toString(),param.get("code").toString());
        //System.out.println(param.get("loginCode").toString());
        //JSONObject openIdObj = WeChatUtil.getSessionKeyOrOpenId(param.get("loginCode").toString());
        if(phoneObj.getObj("errmsg").toString().equals("ok")) {
            String phoneNumber = phoneObj.getJSONObject("phone_info").get("phoneNumber").toString();
//            String openId = openIdObj.getJSONObject("openid").toString();
//            String accessToken = openIdObj.getJSONObject("access_token").toString();
            return upUserService.wxLogin(phoneNumber);
        }else{
            return ResultData.bind(ResultEnum.ACCOUNT_NOT);
        }

        //System.out.println("phone:"+phoneObj.getJSONObject("phone_info").get("phoneNumber").toString());
        // 2.开发者服务器 登录凭证校验接口 appId + appSecret + 接收小程序发送的code
//        JSONObject SessionKeyOpenId = WeChatUtil.getSessionKeyOrOpenId(param.get("code").toString());
//        // 3.接收微信接口服务 获取返回的参数
//        String openid = SessionKeyOpenId.get("openid", String.class);
//        //String openid="oqA6y5ALeRZNIEcC8fUl-zsh12KDTk";
//        String sessionKey = SessionKeyOpenId.get("session_key", String.class);
//        // 用户非敏感信息：rawData
//        // 签名：signature
//        JSONObject rawDataJson = JSONUtil.parseObj(param.get("rawData").toString());
//        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
//        String signature2 = DigestUtils.sha1Hex( param.get("rawData").toString()+ sessionKey);
//        System.out.println("signature2:"+signature2);
//        if (!param.get("signature").toString().equals(signature2)) {
//            return ResultData.error(405,"签名校验失败");
//        }
        //encrypteData比rowData多了appid和openid
//        JSONObject userInfo = WeChatUtil.getUserInfo(param.get("encryptedData").toString(),
//                sessionKey, param.get("iv").toString());
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
//        QueryWrapper<UpUser> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.lambda().eq(UpUser::getOpenId, openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话（或者生成Token）
        //String token = UUID.randomUUID().toString();
//        StpUtil.login(openid);
//        if (upUserService.getUserByOpenId(openid).isEmpty()) {
//            // 用户信息入库
//            String nickName = rawDataJson.get("nickName", String.class);
//            //String phone = userInfo.getJSONObject("phone_info").get("phoneNumber").toString();
////            String avatarUrl = rawDataJson.get("avatarUrl", String.class);
////            String gender = rawDataJson.get("gender", String.class);
////            String city = rawDataJson.get("city", String.class);
////            String country = rawDataJson.get("country", String.class);
////            String province = rawDataJson.get("province", String.class);
//            // 新增用户到数据库
//            upUserService.insertUser(nickName,"",1,nickName,openid,StpUtil.getTokenValue());
////            Long count = lambdaQuery().eq(UpUser::getUserName, nickName)).count();
////            if (count > 0){
////                return ResultData.bind(ResultEnum.ACCOUNT_EXISTS);
////            }
//            System.out.println("user added to database");
//        } else {
//            // 已存在，更新用户登录时间
//            ResultData updateUser=upUserService.updateUserTime(DateTime.now(),openid);
//            if(updateUser.getCode()==200){
//                System.out.println("user updated to database");
//            }
//        }
//        //6. 把新的skey返回给小程序
//        Map<String,String> feedback=new HashMap<>();
//        feedback.put("token",StpUtil.getTokenValue());
//        return ResultData.success(feedback);
//    }
    }

    @PostMapping("wx/logOut")
    @ResponseBody
    public ResultData wxLogOut(){
        return upUserService.logout();
    }
}
