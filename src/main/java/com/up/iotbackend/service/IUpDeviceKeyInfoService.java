package com.up.iotbackend.service;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceKeyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up区域信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-24
 */
public interface IUpDeviceKeyInfoService extends IService<UpDeviceKeyInfo> {
    List<Map<String,Object>> getDeviceKeyByDeviceSn(String deviceSn);
    Map<String,Object> getDeviceKeyByDeviceKeyId(Integer deviceKeyId);
    List<Map<String,Object>> getDeviceKeyByDeviceSnPhone(String deviceSn,String phone);
    List<Map<String,Object>> getDeviceKeyShareInfo(Integer deviceKeyId);
    ResultData updateDeviceKeyInfo(Integer deviceKeyId, String phone, String owner, String startTime, String endTime, DateTime updateTime,String modifier);
    ResultData insertDeviceKeyInfo(String deviceSn,String phone, String owner, String startTime, String endTime,String creator,String modifier);
    ResultData deleteDeviceKey(Integer deviceKeyId,DateTime updateTime,String modifier);
}
