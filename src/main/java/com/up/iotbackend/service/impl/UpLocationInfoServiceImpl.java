package com.up.iotbackend.service.impl;

import com.up.iotbackend.entity.UpLocationInfo;
import com.up.iotbackend.entity.UpVenueInfo;
import com.up.iotbackend.mapper.UpLocationInfoMapper;
import com.up.iotbackend.service.IUpLocationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> getLocationDevicesById(Integer locationId) {
        return upLocationInfoMapper.getLocationDevicesById(locationId);
    }

    @Override
    public List<UpLocationInfo> getAllLocations() {
        return upLocationInfoMapper.getAllLocations();
    }
}
