package com.up.iotbackend.service;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceAdminRef;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备管理员关系信息表 服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
public interface IUpDeviceAdminRefService extends IService<UpDeviceAdminRef> {
    List<Map<String,Object>> getDeviceAdmins(String device_sn);

    ResultData addDeviceAdmin(String deviceSn,String userId,String creator);
}
