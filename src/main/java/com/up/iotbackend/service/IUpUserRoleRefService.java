package com.up.iotbackend.service;

import com.up.iotbackend.entity.UpUserRoleRef;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up用户角色关系信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface IUpUserRoleRefService extends IService<UpUserRoleRef> {
    List<Map<String,Object>> getAllUsersByRoleName(Integer venueId, String roleName);
}
