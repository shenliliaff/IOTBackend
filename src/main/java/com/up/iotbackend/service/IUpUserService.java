package com.up.iotbackend.service;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * up用户信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
public interface IUpUserService extends IService<UpUser> {
    List<UpUser> getUsersByVenue(Integer venueId);
    List<UpUser> getUserByPhone(String phone);
    List<UpUser> getUserByOpenId(String openId);
    //插入用户
    ResultData insertUser(String userName,String phone,Integer venueId,String creator,String openId,String token);
    //更新用户登录时间，open_id已存在
    ResultData updateUserTime(DateTime updateTime, String openId);
    ResultData updateUserToken(String token,String phone,DateTime updateTime);

    ResultData wxLogin(String phoneNumber);

    ResultData logout();
}
