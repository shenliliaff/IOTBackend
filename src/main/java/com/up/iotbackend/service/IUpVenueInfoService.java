package com.up.iotbackend.service;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpVenueInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up场馆信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface IUpVenueInfoService extends IService<UpVenueInfo> {
    List<Map<String,Object>> getVenueLocationDevicesById(Integer venueId);

    List<UpVenueInfo> getAllVenues();
}
