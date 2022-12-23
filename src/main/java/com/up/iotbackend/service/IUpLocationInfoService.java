package com.up.iotbackend.service;

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

    List<UpLocationInfo> getAllLocations();
}
