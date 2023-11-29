package com.up.iotbackend.mapper;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface UpDeviceInfoMapper extends BaseMapper<UpDeviceInfo> {
    @Select("select * from up_device_info d where is_in_use = 1 and d.location_id = #{locationId}")
    List<Map<String, Object>> getDevicesByLocation(Integer locationId);

    @Select("select * from up_device_info d where is_in_use = 1 and d.device_sn = #{deviceSn}")
    List<Map<String, Object>> getDevice(String deviceSn);

    @Select("select device.* from (" +
            "SELECT d.id,d.device_sn,d.device_name,t.id as device_type_id,t.device_type_name,l.device_log_description,l.modifier as user,l.update_time,loca.location_name,loca.id as location_id FROM up_device_info d " +
            "left join up_device_type_info t on d.device_type_id = t.id " +
            "left join up_device_log_info l on l.device_sn = d.device_sn " +
            "left join up_location_info loca on loca.id=d.location_id " +
            "where d.is_in_use = 1 and t.is_in_use = 1 and l.is_in_use = 1 and loca.is_in_use = 1 and d.device_sn=#{deviceSn} and l.device_log_description not like '%心跳%' order by l.update_time desc limit #{pageNum}" +
            ")device group by device.update_time")
    List<Map<String,Object>> getDeviceDetailById(String deviceSn,Integer pageNum);

    @Update("update up_device_info set device_name=#{deviceName},update_time=#{updateTime} where is_in_use = 1 and device_sn=#{deviceSn}")
    void updateDeviceNameInfo(String deviceSn, String deviceName, DateTime updateTime);

    @Update("update up_device_info set location_id=#{LocationId},update_time=#{updateTime} where is_in_use = 1 and device_sn=#{deviceSn}")
    void updateDeviceLocationInfo(String deviceSn, Integer LocationId, DateTime updateTime);
    //关闭已经打开过的记录
    @Update("update up_device_info set remote_operation=#{remoteOperation},update_time=#{updateTime},modifier=#{modifier} where is_in_use = 1 and device_sn=#{deviceSn}")
    void updateDeviceRemoteOperation(String deviceSn,String remoteOperation, DateTime updateTime,String modifier);

    @Select("select d.device_sn,d.modifier from up_device_info d where device_sn=#{deviceSn} and is_in_use = 1 and d.remote_operation = #{remoteOperation} order by update_time")
    List<Map<String,Object>> getDeviceRemoteOperation(String deviceSn,String remoteOperation);
}
