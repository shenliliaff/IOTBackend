package com.up.iotbackend.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.up.iotbackend.entity.*;
import com.up.iotbackend.mapper.UpDeviceInfoMapper;
import com.up.iotbackend.mapper.UpLocationInfoMapper;
import com.up.iotbackend.service.IUpLocationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * up区域信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Service
public class UpLocationInfoServiceImpl extends ServiceImpl<UpLocationInfoMapper, UpLocationInfo> implements IUpLocationInfoService {

    @Resource
    UpLocationInfoMapper upLocationInfoMapper;

    @Resource
    UpDeviceInfoMapper upDeviceInfoMapper;

    @Override
    public List<Map<String, Object>> getLocationDevicesById(Integer locationId) {
        return upLocationInfoMapper.getLocationDevicesById(locationId);
    }

    @Override
    public List<UpLocationInfo> getAllLocations(Integer venueId) {
        return upLocationInfoMapper.getAllLocations(venueId);
    }

    @Override
    public ResultData addLocation(Integer venueId,String locationName, String creator) {
        Long count = lambdaQuery().eq(UpLocationInfo::getVenueId, venueId)
                .eq(UpLocationInfo::getLocationName,locationName)
                .eq(UpLocationInfo::getIsInUse,1)
                .count();
        if(count > 0){
            return ResultData.bind(ResultEnum.INSERT_FAILURE);
        }

        UpLocationInfo location= new UpLocationInfo();
        location.setLocationName(locationName);
        location.setVenueId(venueId);
        location.setCreator(creator);
        location.setIsInUse(1);
        location.setModifier(creator);
        location.setUpdateTime(LocalDateTime.now());
        save(location);

        UpLocationInfo one= this.getOne(new LambdaQueryWrapper<UpLocationInfo>()
                .eq(UpLocationInfo::getVenueId, venueId)
                .eq(UpLocationInfo::getLocationName,locationName));
        return ResultData.success(one);
//        try {
//            upLocationInfoMapper.addOneLocation(Integer.parseInt(venueId), creator);
//            List<Map<String,Object>> location = upLocationInfoMapper.getNewLocation();
//            return ResultData.success(location);
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResultData.bind(ResultEnum.INSERT_FAILURE);
//        }
    }

    @Override
    public ResultData updateLocation(Integer locationId, String locationName, String modifier) {
        Long count = lambdaQuery().eq(UpLocationInfo::getId, locationId).eq(UpLocationInfo::getIsInUse,1).count();
        if(count == 0){
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
        lambdaUpdate()
                .eq(UpLocationInfo::getId,locationId)
                .set(UpLocationInfo::getLocationName,locationName)
                .set(UpLocationInfo::getModifier,modifier)
                .set(UpLocationInfo::getUpdateTime,DateTime.now())
                .update();
        UpLocationInfo one= this.getOne(new LambdaQueryWrapper<UpLocationInfo>()
                .eq(UpLocationInfo::getId, locationId));
        return ResultData.success(one);
    }

    @Override
    public ResultData deleteLocation(Integer locationId, String modifier) {
        Long count = lambdaQuery().eq(UpLocationInfo::getId, locationId).eq(UpLocationInfo::getIsInUse,1).count();
        if(count == 0){
            return ResultData.bind(ResultEnum.DELETE_FAILURE);
        }
        lambdaUpdate()
                .eq(UpLocationInfo::getId,locationId)
                .set(UpLocationInfo::getIsInUse,0)
                .set(UpLocationInfo::getModifier,modifier)
                .set(UpLocationInfo::getUpdateTime,DateTime.now())
                .update();
        return ResultData.success();
    }

    @Override
    public ResultData updateLocationName(Integer locationId, String locationName, String modifier, DateTime updateTime,String area,String volume,String desc,String image) {
        try {
            if (locationId == null) {
                return ResultData.bind(ResultEnum.PARAMS);
            }
            String remark="{\"area\":\""+area+"\",\"volume\":\""+volume+"\",\"desc\":\""+desc+"\",\"image\":\""+image+"\"}";
            upLocationInfoMapper.updateLocationInfo(locationId, locationName, modifier, updateTime,remark);
            return ResultData.success();
        } catch (Exception e) {
            //System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }

    @Override
    public List<Map<String, Object>> getDevicesByVenueId(Integer venueId) {
        return upLocationInfoMapper.getDevicesByVenueId(venueId);
    }

    @Override
    public List<Map<String,Object>> getDevicesByVenueIdPad(Integer venueId) {
        //获取venue场馆下所有的教室
        List<UpLocationInfo> locations = this.lambdaQuery().eq(UpLocationInfo::getVenueId,venueId).eq(UpLocationInfo::getIsInUse,1).list();
        //System.out.println(locations);
        List<Map<String,Object>> feedback = new ArrayList<>();
        if(!Objects.isNull(locations)){
            String locationId="";
            String locationName;
            int i;
            for(i = 0; i < locations.size(); i++) {
                locationId = locations.get(i).getId().toString();
                locationName = locations.get(i).getLocationName();
                //System.out.println(locationId);
                //获取教室location下所有的设备
                List<UpDeviceInfo> devices = upDeviceInfoMapper.selectList(new LambdaQueryWrapper<UpDeviceInfo>().eq(UpDeviceInfo::getLocationId, locationId).eq(UpDeviceInfo::getIsInUse, 1));
                //System.out.println(devices);
                if (!Objects.isNull(devices)) {
                    for (int j = 0; j < devices.size(); j++) {
                        HashMap<String, Object> feedbackMap = new HashMap<>();
                        feedbackMap.put("device_name", devices.get(j).getDeviceName());
                        feedbackMap.put("device_sn", devices.get(j).getDeviceSn());
                        feedbackMap.put("location_name", locationName);
                        feedbackMap.put("device_type_id", devices.get(j).getDeviceTypeId());
                        feedbackMap.put("location_id", locationId);
                        feedbackMap.put("isConnect", "在线");
                        feedback.add(feedbackMap);
                    }
                }
            }
            //System.out.println(feedback);
        }
        return feedback;
    }

    @Override
    public List<Map<String,Object>> getDevicesByLocationIdPad(String locationId) {
        List<Map<String,Object>> feedback = new ArrayList<>();
        UpLocationInfo location = this.lambdaQuery().eq(UpLocationInfo::getId,locationId).eq(UpLocationInfo::getIsInUse,1).one();
        //System.out.println(location);
        List<UpDeviceInfo> devices = upDeviceInfoMapper.selectList(new LambdaQueryWrapper<UpDeviceInfo>().eq(UpDeviceInfo::getLocationId, locationId).eq(UpDeviceInfo::getIsInUse, 1));
        //System.out.println(devices);
        //System.out.println(devices);
        if (!Objects.isNull(devices) && !Objects.isNull(location)) {
            for (int j = 0; j < devices.size(); j++) {
                HashMap<String, Object> feedbackMap = new HashMap<>();
                feedbackMap.put("device_name", devices.get(j).getDeviceName());
                feedbackMap.put("device_sn", devices.get(j).getDeviceSn());
                feedbackMap.put("location_name", location.getLocationName());
                feedbackMap.put("device_type_id", devices.get(j).getDeviceTypeId());
                feedbackMap.put("location_id", locationId);
                feedbackMap.put("isConnect", "在线");
                feedback.add(feedbackMap);
            }
        }
        return feedback;
    }

    @Override
    public ResultData getLocationDetailInfo(String deviceSn) {
        UpLocationInfo location;
        if (deviceSn == null) {
            return ResultData.bind(ResultEnum.PARAMS);
        }
        List<UpDeviceInfo> deviceInfoList = upDeviceInfoMapper.selectList(new LambdaQueryWrapper<UpDeviceInfo>().eq(UpDeviceInfo::getDeviceSn,deviceSn).eq(UpDeviceInfo::getIsInUse,1));
        if(deviceInfoList.size() != 1) {
            return ResultData.error(500,"one location have two or 0 devices. these are not allowed");
        }else {
            location = this.getOne(new LambdaQueryWrapper<UpLocationInfo>().eq(UpLocationInfo::getId, deviceInfoList.get(0).getLocationId()).eq(UpLocationInfo::getIsInUse, 1));
        }
        if (Objects.isNull(location)) {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
        return ResultData.success(location);
    }
}
