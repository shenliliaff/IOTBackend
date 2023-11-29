package com.up.iotbackend.mapper;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.TblUser;
import com.up.iotbackend.entity.UpLocationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
    @Select("select * from up_location_info l where l.is_in_use = 1 and l.venue_id=#{venueId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "locationName", column = "location_name"),
            @Result(property = "venueId", column = "venue_id"),
    })
    List<UpLocationInfo> getAllLocations(Integer venueId);

//    @Select("select d.device_sn,l.location_name,l.id as location_id,d.device_sn,d.device_name,t.device_type_name,d.update_time FROM up_location_info l " +
//            "left join up_device_info d on l.id = d.location_id " +
//            "left join up_device_type_info t on d.device_type_id = t.id " +
//            "where l.is_in_use = 1 and d.is_in_use = 1 and l.id = #{locationId}")
    @Select("select d.device_sn,l.location_name,l.id as location_id,d.device_name,t.device_type_name,log.update_time FROM up_location_info l \n" +
            "left join up_device_info d on l.id = d.location_id \n" +
            "left join up_device_type_info t on d.device_type_id = t.id \n" +
            "left join up_device_log_info log on d.device_sn = log.device_sn\n" +
            "where l.is_in_use = 1 and d.is_in_use = 1 and l.id = #{locationId} and log.is_in_use = 1 and log.device_log_description like \"%心跳%\"")
    List<Map<String,Object>> getLocationDevicesById(Integer locationId);

    @Select("select d.device_sn,v.venue_name,l.id as location_id,l.remark as location_detail,d.remark as device_detail,l.location_name,d.device_name,t.device_type_name,log.update_time from up_venue_info v \n" +
            "left join up_location_info l on l.venue_id = v.id\n" +
            "left join up_device_info d on d.location_id = l.id \n" +
            "left join up_device_type_info t on d.device_type_id = t.id \n" +
            "left join up_device_log_info log on d.device_sn = log.device_sn\n" +
            "where v.is_in_use = 1 and l.is_in_use = 1 and d.is_in_use = 1 and v.id = #{venueId} and log.is_in_use = 1 and log.device_log_description like \"%心跳%\"")
    List<Map<String,Object>> getDevicesByVenueId(Integer venueId);

    @Select("select * from up_location_info l where l.is_in_use = 1 order by update_time desc limit 1")
    List<Map<String,Object>> getNewLocation();

    @Insert("insert into up_location_info(location_name,venue_id,is_in_use,creator,modifier) value" +
            "('新场地',#{venueId},1,#{creator},#{creator})")
    void addOneLocation(Integer venueId,String creator);

    @Update("update up_location_info set location_name=#{locationName},update_time=#{updateTime},modifier=#{modifier},remark=#{remark} where id=#{locationId}")
    void updateLocationInfo(Integer locationId, String locationName, String modifier,DateTime updateTime,String remark);
}
