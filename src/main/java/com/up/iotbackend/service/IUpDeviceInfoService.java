package com.up.iotbackend.service;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * up设备信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface IUpDeviceInfoService extends IService<UpDeviceInfo> {
    ResultData getDevicesByLocation(Integer location_id);
}
