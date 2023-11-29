package com.up.iotbackend.service;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface IUpDeviceInfoService extends IService<UpDeviceInfo> {
    List<Map<String, Object>> getDevicesByLocation(Integer location_id);
    List<Map<String,Object>> getDeviceDetailById(String device_sn,Integer pageNum);
    List<Map<String,Object>> getDeviceById(String device_sn);
    ResultData updateDeviceNameInfo(String device_sn, String deviceName, DateTime updateTime);
    ResultData updateDeviceLocationInfo(String device_sn, String LocationId, DateTime updateTime);
    ResultData updateDeviceRemoteOperation(String deviceSn, String remoteOperation, DateTime updateTime,String modifier);
    ResultData getDevicePadDetailByDeviceSn(String device_sn);
    ResultData updateDevicePadDetailByDeviceSn(String device_sn,String bgImg,String type,String log,String selfDefine,String dataSourceType,String dataSource,String versionCode,String bannerImages,String otherUrl,String updateUrl);
}
