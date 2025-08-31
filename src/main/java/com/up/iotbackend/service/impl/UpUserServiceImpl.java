package com.up.iotbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpUser;
import com.up.iotbackend.entity.UpVenueInfo;
import com.up.iotbackend.mapper.UpUserMapper;
import com.up.iotbackend.mapper.UpVenueInfoMapper;
import com.up.iotbackend.service.IUpUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * up用户信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
@Service
public class UpUserServiceImpl extends ServiceImpl<UpUserMapper, UpUser> implements IUpUserService {

    @Resource
    UpUserMapper upUserMapper;
    @Resource
    UpVenueInfoMapper upVenueInfoMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public List<UpUser> getUsersByVenue(Integer venueId) {
        
        return upUserMapper.getUsersByVenue(venueId);
    }

    //根据手机号拿用户信息
    @Override
    public List<UpUser> getUserByPhone(String phone) {
        return upUserMapper.getUserByPhone(phone);
    }

    @Override
    public List<UpUser> getUserByOpenId(String openId) {
        return upUserMapper.getUserByOpenId(openId);
    }

    @Override
    public ResultData insertUser(String username, String phone, Integer venueId, String creator, String openId, String token) {
        try {
            upUserMapper.insertUser(username,phone,venueId,creator,openId,token);
            return ResultData.success();
        }catch (Exception e){
            //System.out.println(e);
            return ResultData.bind(ResultEnum.INSERT_FAILURE);
        }
    }

    @Override
    public ResultData updateUserTime(DateTime updateTime, String openId) {
        try {
            if(upUserMapper.getUserByOpenId(openId).isEmpty()){
                return ResultData.bind(ResultEnum.MESSAGE_NOT);
            }else {
                upUserMapper.updateUserTime(updateTime,openId);
                return ResultData.success();
            }
        }catch (Exception e){
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public ResultData updateUserToken(String token, String phone,DateTime updateTime) {
        try {
            if(upUserMapper.getUserByPhone(phone).isEmpty()){
                return ResultData.bind(ResultEnum.MESSAGE_NOT);
            }else {
                upUserMapper.updateUserToken(token,phone,updateTime);
                return ResultData.success();
            }
        }catch (Exception e){
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public ResultData wxLogin(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)){
            return ResultData.bind(ResultEnum.PARAMS);
        }
        UpUser one= this.getOne(new LambdaQueryWrapper<UpUser>().eq(UpUser::getPhone, phoneNumber).eq(UpUser::getIsInUse, 1));
        if (Objects.isNull(one)){
            return ResultData.bind(ResultEnum.ACCOUNT_NOT);
        }
        StpUtil.login(one.getId());
        UpVenueInfo oneVenue=upVenueInfoMapper.selectOne(new LambdaQueryWrapper<UpVenueInfo>().eq(UpVenueInfo::getId, one.getVenueId()).eq(UpVenueInfo::getIsInUse,1));
        if (StringUtils.isNotEmpty(StpUtil.getTokenValue())){
            lambdaUpdate()
                    .set(UpUser::getToken,StpUtil.getTokenValue())
                    .set(UpUser::getUpdateTime,DateTime.now())
                    //.set(UpUser::getOpenId,openId)
                    .eq(UpUser::getId,one.getId())
                    .update();
        }
        Map<String,Object> result = new HashMap<>();
        result.put("token", StpUtil.getTokenValue());
        //result.put("open_id", openId);
        result.put("phone",phoneNumber);
        result.put("venue_id",one.getVenueId());
        result.put("venue_name",oneVenue.getVenueName());
        //result.put("access_token",accessToken);
        //StpUtil.getSession().set("language", Optional.ofNullable(one.getLanguage()).orElse("en"));
        return ResultData.success(result);
    }

    @Override
    public ResultData logout() {
        long userId = StpUtil.getLoginIdAsInt();
        lambdaUpdate()
                .set(UpUser::getToken,null)
                .eq(UpUser::getId,userId)
                .update();

        StpUtil.logout();
        return ResultData.success();
    }
}
