package com.up.iotbackend.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.up.iotbackend.entity.*;
import com.up.iotbackend.mapper.UpDeviceInfoMapper;
import com.up.iotbackend.service.IUpDeviceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * up设备信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Service
public class UpDeviceInfoServiceImpl extends ServiceImpl<UpDeviceInfoMapper, UpDeviceInfo> implements IUpDeviceInfoService {

    @Resource
    UpDeviceInfoMapper upDeviceInfoMapper;

    @Override
    public List<Map<String, Object>> getDevicesByLocation(Integer location_id) {
        return upDeviceInfoMapper.getDevicesByLocation(location_id);
    }

    @Override
    public List<Map<String, Object>> getDeviceDetailById(String device_sn, Integer pageNum) {
        return upDeviceInfoMapper.getDeviceDetailById(device_sn, pageNum);
    }

    @Override
    public Map<String, Object> getDeviceVenueInfoById(String device_sn) {
        return upDeviceInfoMapper.getDeviceVenueInfoById(device_sn);
    }

    @Override
    public List<Map<String, Object>> getDeviceById(String device_sn) {
        return upDeviceInfoMapper.getDevice(device_sn);
    }

    @Override
    public ResultData updateDeviceNameInfo(String device_sn, String deviceName, DateTime updateTime) {
        try {
            upDeviceInfoMapper.updateDeviceNameInfo(device_sn, deviceName, updateTime);
            return ResultData.success();
        } catch (Exception e) {
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public ResultData updateDeviceLocationInfo(String device_sn, String LocationId, DateTime updateTime) {
        try {
            upDeviceInfoMapper.updateDeviceLocationInfo(device_sn, Integer.parseInt(LocationId), updateTime);
            return ResultData.success();
        } catch (Exception e) {
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public ResultData updateDeviceRemoteOperation(String deviceSn, String remoteOperation, DateTime updateTime, String modifier) {
        try {
            if (upDeviceInfoMapper.getDevice(deviceSn).isEmpty()) {
                return ResultData.bind(ResultEnum.UPDATE_FAILURE);
            } else {
                upDeviceInfoMapper.updateDeviceRemoteOperation(deviceSn, remoteOperation, updateTime, modifier);
                return ResultData.success();
            }
        } catch (Exception e) {
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public ResultData getDevicePadDetailByDeviceSn(String device_sn) {
        if (StringUtils.isEmpty(device_sn)) {
            return ResultData.bind(ResultEnum.PARAMS);
        }
        UpDeviceInfo one = this.getOne(new LambdaQueryWrapper<UpDeviceInfo>().eq(UpDeviceInfo::getDeviceSn, device_sn).eq(UpDeviceInfo::getIsInUse, 1));
        if (Objects.isNull(one)) {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
        return ResultData.success(one);
    }

    @Override
    public ResultData updateDevicePadDetailByDeviceSn(String device_sn,String bgImg,String type,String log,String selfDefine,String dataSourceType,String dataSource,String versionCode,String bannerImages,String otherUrl,String updateUrl) {
        if (StringUtils.isEmpty(device_sn)) {
            return ResultData.bind(ResultEnum.PARAMS);
        }
        UpDeviceInfo one = this.getOne(new LambdaQueryWrapper<UpDeviceInfo>().eq(UpDeviceInfo::getDeviceSn, device_sn).eq(UpDeviceInfo::getIsInUse, 1));
        if (Objects.isNull(one)) {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
        String padDetailInfo= "{\"type\":\""+type+"\",\"bgImg\":\""+bgImg+"\",\"logo\":\""+log+"\",\"selfDefine\":\""+selfDefine+"\",\"dataSourceType\":\""+dataSourceType+"\",\"dataSource\":\""+dataSource+"\",\"versionCode\":\""+versionCode+"\",\"bannerImages\":\""+bannerImages+"\",\"otherUrl\":\""+otherUrl+"\",\"updateUrl\":\""+updateUrl+"\"}";
//        HashMap<String,Object> padDetailInfo=new HashMap<>();
//        padDetailInfo.put(bgImg",bgImg);
//        padDetailInfo.put("type",type);
//        padDetailInfo.put("log",log);
//        padDetailInfo.put("selfDefine",selfDefine);
//        padDetailInfo.put("dataSourceType",dataSourceType);
//        padDetailInfo.put("dataSource",dataSource);
        lambdaUpdate().eq(UpDeviceInfo::getDeviceSn,device_sn)
                    .set(UpDeviceInfo::getRemark, padDetailInfo)
                    .update();
        return ResultData.success();
    }
}
