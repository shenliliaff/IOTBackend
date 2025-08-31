package com.up.iotbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.UpDeviceAdminRef;
import com.up.iotbackend.mapper.UpDeviceAdminRefMapper;
import com.up.iotbackend.mapper.UpDeviceInfoMapper;
import com.up.iotbackend.mapper.UpUserMapper;
import com.up.iotbackend.service.IUpDeviceAdminRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备管理员关系信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
@Service
public class UpDeviceAdminRefServiceImpl extends ServiceImpl<UpDeviceAdminRefMapper, UpDeviceAdminRef> implements IUpDeviceAdminRefService {

    @Resource
    UpDeviceAdminRefMapper upDeviceAdminRefMapper;
    @Resource
    UpDeviceInfoMapper upDeviceInfoMapper;
    @Resource
    UpUserMapper upUserMapper;
    @Override
    public List<Map<String, Object>> getDeviceAdmins(String device_sn) {
        return upDeviceAdminRefMapper.getDeviceAdmins(device_sn);
    }

    @Override
    public ResultData addDeviceAdmin(String deviceSn, String userId, String creator) {
        if(upDeviceInfoMapper.getDevice(deviceSn).isEmpty()){
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        } else if (upUserMapper.getUserByUserId(Integer.parseInt(userId)).isEmpty()) {
            return ResultData.bind(ResultEnum.MESSAGE_NOT);
        }
        if(upDeviceAdminRefMapper.getDeviceAdmin(deviceSn,Integer.parseInt(userId)).isEmpty()){
            upDeviceAdminRefMapper.addDeviceAdmin(deviceSn,Integer.parseInt(userId), deviceSn+"管理员"+userId,creator);
            return ResultData.success();
        }else{
            return ResultData.success();
        }

    }
}
