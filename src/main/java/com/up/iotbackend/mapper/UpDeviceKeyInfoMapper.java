package com.up.iotbackend.mapper;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.UpDeviceInfo;
import com.up.iotbackend.entity.UpDeviceKeyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up区域信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-24
 */
public interface UpDeviceKeyInfoMapper extends BaseMapper<UpDeviceKeyInfo> {
    //获取钥匙列表
    @Select("select * from up_device_key_info where device_sn=#{deviceSn} and is_in_use = 1 order by update_time")
    List<Map<String,Object>> getDeviceKeyByDeviceSn(String deviceSn);
    @Select("select * from up_device_key_info where id=#{deviceKeyId} and is_in_use = 1")
    Map<String,Object> getDeviceKeyByDeviceKeyId(Integer deviceKeyId);
    @Select("select * from up_device_key_info where device_sn=#{deviceSn} and phone=#{phone} and is_in_use = 1")
    List<Map<String,Object>> getDeviceKeyByDeviceSnPhone(String deviceSn,String phone);
    //获取分享钥匙页面的详情
    @Select("select k.start_time,k.end_time,k.phone,k.owner,l.location_name,l.id as location_id,v.venue_name,v.id as venue_id,d.device_name,d.id as device_id from up_device_key_info k " +
            "left join up_device_info d on d.device_sn = k.device_sn " +
            "left join up_location_info l on l.id = d.location_id " +
            "left join up_venue_info v on v.id = l.venue_id " +
            "where k.id=#{deviceKeyId} and d.is_in_use = 1 and l.is_in_use = 1 and v.is_in_use = 1 and k.is_in_use = 1")
    List<Map<String,Object>> getDeviceKeyShareInfo(Integer deviceKeyId);
    //抓取记录更新者
    //更新钥匙信息
    @Update("update up_device_key_info set phone=#{phone},owner=#{owner},start_time=#{startTime},end_time=#{endTime},update_time=#{updateTime},modifier=#{modifier} where id=#{deviceKeyId} and is_in_use = 1")
    void updateDeviceKeyInfo(Integer deviceKeyId, String phone, String owner, String startTime,String endTime,DateTime updateTime,String modifier);
    //创建新钥匙信息
    @Insert("insert into up_device_key_info (device_sn,phone,owner,start_time,end_time,is_in_use,creator,modifier) value (#{deviceSn},#{phone},#{owner},#{startTime},#{endTime},1,#{creator},#{modifier})")
    void insertDeviceKeyInfo(String deviceSn, String phone, String owner, String startTime,String endTime,String creator,String modifier);
    //删除钥匙
    @Update("update up_device_key_info set update_time=#{updateTime},is_in_use = 0,modifier=#{modifier} where id=#{deviceKeyId} and is_in_use = 1")
    void deleteDeviceKey(Integer deviceKeyId,DateTime updateTime,String modifier);
}
