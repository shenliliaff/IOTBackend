package com.up.iotbackend.mapper;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * up设备信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface UpDeviceInfoMapper extends BaseMapper<UpDeviceInfo> {
    @Select("select * from up_device_info d where d.location_id = #{location_id}")
    ResultData getDevicesByLocation(Integer location_id);
}
