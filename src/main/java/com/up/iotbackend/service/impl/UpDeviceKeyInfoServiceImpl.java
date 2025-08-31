package com.up.iotbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpDeviceKeyInfo;
import com.up.iotbackend.mapper.UpDeviceKeyInfoMapper;
import com.up.iotbackend.service.IUpDeviceKeyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up区域信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-24
 */
@Service
public class UpDeviceKeyInfoServiceImpl extends ServiceImpl<UpDeviceKeyInfoMapper, UpDeviceKeyInfo> implements IUpDeviceKeyInfoService {

    @Resource
    UpDeviceKeyInfoMapper upDeviceKeyInfoMapper;

    @Override
    public List<Map<String,Object>> getDeviceKeyByDeviceSn(String deviceSn) {
        return upDeviceKeyInfoMapper.getDeviceKeyByDeviceSn(deviceSn);
    }

    @Override
    public Map<String, Object> getDeviceKeyByDeviceKeyId(Integer deviceKeyId) {
        return upDeviceKeyInfoMapper.getDeviceKeyByDeviceKeyId(deviceKeyId);
    }

    @Override
    public List<Map<String,Object>> getDeviceKeyByDeviceSnPhone(String deviceSn,String phone) {
        return upDeviceKeyInfoMapper.getDeviceKeyByDeviceSnPhone(deviceSn,phone);
    }

    @Override
    public List<Map<String, Object>> getDeviceKeyShareInfo(Integer deviceKeyId) {
        return upDeviceKeyInfoMapper.getDeviceKeyShareInfo(deviceKeyId);
    }

    @Override
    public ResultData updateDeviceKeyInfo(Integer deviceKeyId, String phone, String owner, String startTime, String endTime, DateTime updateTime,String modifier) {
        try {
            if(upDeviceKeyInfoMapper.getDeviceKeyByDeviceKeyId(deviceKeyId).isEmpty()){
                return ResultData.bind(ResultEnum.MESSAGE_NOT);
            }else {
                upDeviceKeyInfoMapper.updateDeviceKeyInfo(deviceKeyId, phone, owner, startTime, endTime, updateTime,modifier);
                return ResultData.success();
            }
        }catch (Exception e){
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public ResultData insertDeviceKeyInfo(String deviceSn,String phone, String owner, String startTime, String endTime,String creator,String modifier) {
        try {
            //System.out.println(deviceSn);
            upDeviceKeyInfoMapper.insertDeviceKeyInfo(deviceSn,phone,owner,startTime,endTime,creator,modifier);
            return ResultData.success();
        }catch (Exception e){
            //System.out.println(e);
            return ResultData.bind(ResultEnum.INSERT_FAILURE);
        }
    }

    @Override
    public ResultData deleteDeviceKey(Integer deviceKeyId, DateTime updateTime,String modifier) {
        try {
            upDeviceKeyInfoMapper.deleteDeviceKey(deviceKeyId,updateTime,modifier);
            return ResultData.success();
        }catch (Exception e){
            //System.out.println(e);
            return ResultData.bind(ResultEnum.DELETE_FAILURE);
        }
    }
}
