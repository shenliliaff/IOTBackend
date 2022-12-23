package com.up.iotbackend.mapper;

import com.up.iotbackend.entity.TblUser;
import com.up.iotbackend.entity.UpVenueInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up场馆信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface UpVenueInfoMapper extends BaseMapper<UpVenueInfo> {
    @Select("select * from up_venue_info v where v.is_in_use = 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "venueName", column = "venue_name")
    })
    List<UpVenueInfo> getAllVenues();

    @Select("SELECT v.id,l.location_name,d.device_sn,d.device_name FROM up_venue_info v " +
            "left join up_location_info l on v.id = l.venue_id left join up_device_info d on l.id = d.location_id " +
            "where v.is_in_use = 1 and l.is_in_use = 1 and d.is_in_use = 1 and v.id = #{venue_id}")
    List<Map<String,Object>> getVenueLocationDevicesById(Integer venue_id);
}
