package com.up.iotbackend.mapper;

import com.up.iotbackend.entity.TblUser;
import com.up.iotbackend.entity.UpLocationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up区域信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface UpLocationInfoMapper extends BaseMapper<UpLocationInfo> {
    @Select("select * from up_location_info l where l.is_in_use = 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "locationName", column = "location_name")
    })
    List<UpLocationInfo> getAllLocations();

    @Select("SELECT l.location_name,d.device_sn,d.device_name FROM up_location_info l " +
            "left join up_device_info d on l.id = d.location_id " +
            "where l.is_in_use = 1 and d.is_in_use = 1 and l.id = #{location_id}")
    List<Map<String,Object>> getLocationDevicesById(Integer location_id);
}
