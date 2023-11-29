package com.up.iotbackend.service;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceLogInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备记录信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface IUpDeviceLogInfoService extends IService<UpDeviceLogInfo> {
    List<Map<String,Object>> getDeviceLogByDeviceSn(String deviceSn,Integer pageNum);
    //获取心跳的是否有log记录
    List<Map<String,Object>> getDeviceLogQueryCmdByDeviceSn(String deviceSn);
    List<Map<String,Object>> getDeviceRemoteOperation(String deviceSn,String remoteOperation);
    ResultData insertDeviceLogInfo(String deviceSn, String log, String creator);
    ResultData updateQueryCmd(Integer deviceKeyId,String DoorMagnetic,DateTime updateTime);



}
