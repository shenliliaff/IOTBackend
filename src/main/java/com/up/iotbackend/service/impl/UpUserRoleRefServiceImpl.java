package com.up.iotbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.up.iotbackend.entity.UpUserRoleRef;
import com.up.iotbackend.mapper.UpUserRoleRefMapper;
import com.up.iotbackend.service.IUpUserRoleRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up用户角色关系信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Service
public class UpUserRoleRefServiceImpl extends ServiceImpl<UpUserRoleRefMapper, UpUserRoleRef> implements IUpUserRoleRefService {

    @Resource
    UpUserRoleRefMapper upUserRoleRefMapper;

    @Override
    public List<Map<String, Object>> getAllUsersByRoleName(Integer venueId, String roleName) {
        
        return upUserRoleRefMapper.getAllUsersByRoleName(venueId,roleName);
    }
}
