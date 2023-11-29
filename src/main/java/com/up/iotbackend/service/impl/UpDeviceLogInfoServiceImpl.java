package com.up.iotbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpDeviceLogInfo;
import com.up.iotbackend.mapper.UpDeviceLogInfoMapper;
import com.up.iotbackend.service.IUpDeviceLogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备记录信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Service
public class UpDeviceLogInfoServiceImpl extends ServiceImpl<UpDeviceLogInfoMapper, UpDeviceLogInfo> implements IUpDeviceLogInfoService {
    @Resource
    UpDeviceLogInfoMapper upDeviceLogInfoMapper;
    @Override
    public List<Map<String,Object>> getDeviceLogByDeviceSn(String deviceSn,Integer pageNum) {
        
        return upDeviceLogInfoMapper.getDeviceLogByDeviceSn(deviceSn,pageNum);
    }

    @Override
    public List<Map<String, Object>> getDeviceLogQueryCmdByDeviceSn(String deviceSn) {
        
        return upDeviceLogInfoMapper.getDeviceLogQueryCmdByDeviceSn(deviceSn);
    }

    @Override
    public List<Map<String, Object>> getDeviceRemoteOperation(String device_sn,String remoteOperation) {
        
        return upDeviceLogInfoMapper.getDeviceRemoteOperation(device_sn, remoteOperation);
    }

    @Override
    public ResultData insertDeviceLogInfo(String deviceSn, String log, String creator) {
        
        try {
            upDeviceLogInfoMapper.insertDeviceLogInfo(deviceSn,log,creator);
            return ResultData.success();
        }catch (Exception e){
            System.out.println(e);
            return ResultData.bind(ResultEnum.INSERT_FAILURE);
        }
    }

    @Override
    public ResultData updateQueryCmd(Integer deviceKeyId, String DoorMagnetic, DateTime updateTime) {
        
        try {
            upDeviceLogInfoMapper.updateQueryCmd(deviceKeyId,"心跳自动更新-"+DoorMagnetic,updateTime);
            return ResultData.success();
        }catch (Exception e){
            System.out.println(e);
            return ResultData.bind(ResultEnum.UPDATE_FAILURE);
        }
    }


}
