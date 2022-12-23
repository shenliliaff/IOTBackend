package com.up.iotbackend.service.impl;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.up.iotbackend.mapper.UpDeviceInfoMapper;
import com.up.iotbackend.mapper.UpVenueInfoMapper;
import com.up.iotbackend.service.IUpDeviceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public ResultData getDevicesByLocation(Integer location_id) {
        return upDeviceInfoMapper.getDevicesByLocation(location_id);
    }
}
