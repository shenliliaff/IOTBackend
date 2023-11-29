package com.up.iotbackend.service;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.up.iotbackend.entity.UpLocationInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.up.iotbackend.entity.UpVenueInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up区域信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface IUpLocationInfoService extends IService<UpLocationInfo> {
    List<Map<String,Object>> getLocationDevicesById(Integer locationId);

    List<UpLocationInfo> getAllLocations(Integer venueId);

    ResultData addLocation(Integer venueId,String locationName, String creator);

    ResultData updateLocation(Integer locationId, String locationName, String modifier);

    ResultData deleteLocation(Integer locationId,String modifier);

    ResultData updateLocationName(Integer locationId, String locationName,String modifier, DateTime updateTime,String area,String volume,String desc,String image);
    ResultData getLocationDetailInfo(String deviceSn);
    List<Map<String, Object>> getDevicesByVenueId(Integer venueId);
    List<Map<String, Object>> getDevicesByVenueIdPad(Integer venueId);
    List<Map<String, Object>> getDevicesByLocationIdPad(String locationId);
}
