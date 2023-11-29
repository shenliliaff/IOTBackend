package com.up.iotbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpVenueInfo;
import com.up.iotbackend.mapper.UpVenueInfoMapper;
import com.up.iotbackend.service.IUpVenueInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up场馆信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Service
public class UpVenueInfoServiceImpl extends ServiceImpl<UpVenueInfoMapper, UpVenueInfo> implements IUpVenueInfoService {

    @Resource
    UpVenueInfoMapper  upVenueInfoMapper;

    @Override
    public List<Map<String,Object>> getVenueLocationDevicesById(Integer venueId) {
        
        return upVenueInfoMapper.getVenueLocationDevicesById(venueId);
    }

    @Override
    public List<Map<String,Object>> getAllVenues() {
        
        return upVenueInfoMapper.getAllVenues();
    }
}
