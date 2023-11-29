package com.up.iotbackend.mapper;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.UpDeviceKeyInfo;
import com.up.iotbackend.entity.UpDeviceLogInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备记录信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface UpDeviceLogInfoMapper extends BaseMapper<UpDeviceLogInfo> {
    //获取门禁记录列表
    @Select("select device.* from (" +
            "select l.device_sn,l.update_time,l.modifier,l.id,l.device_log_description,d.device_name from up_device_log_info l " +
            "left join up_device_info d on d.device_sn = l.device_sn " +
            "where l.device_sn=#{deviceSn} and l.is_in_use = 1 and d.is_in_use = 1 order by l.update_time desc limit #{pageNum}" +
            ")device group by device.update_time")
    List<Map<String,Object>> getDeviceLogByDeviceSn(String deviceSn,Integer pageNum);

    //查看是否需要有心跳记录
    @Select("select * from up_device_log_info where device_sn=#{deviceSn} and device_log_description like '%心跳自动更新%' and is_in_use = 1 order by update_time desc limit 1")
    List<Map<String,Object>> getDeviceLogQueryCmdByDeviceSn(String deviceSn);

    //获取所有需要远程更新的设备
    @Select("select d.device_sn,d.modifier from up_device_info d where device_sn=#{deviceSn} and is_in_use = 1 and d.remote_operation = #{remoteOperation} order by update_time")
    List<Map<String,Object>> getDeviceRemoteOperation(String deviceSn,String remoteOperation);
    //插入log记录
    @Insert("insert into up_device_log_info (device_sn,device_log_description,is_in_use,creator,modifier) value (" +
            "#{deviceSn},#{log},1,#{creator},#{creator})")
    void insertDeviceLogInfo(String deviceSn, String log,String creator);
    //关闭已经打开过的记录
    @Update("update up_device_info set update_time=#{updateTime},remote_operation=#{remoteOperation} where id=#{deviceSn} and is_in_use = 1")
    void updateRemoteOperation(String deviceSn, DateTime updateTime,String remoteOperation);
    //更新心跳
    @Update("update up_device_log_info set update_time=#{updateTime}, device_log_description=#{DoorMagnetic} where id=#{deviceKeyId} and is_in_use = 1")
    void updateQueryCmd(Integer deviceKeyId,String DoorMagnetic,DateTime updateTime);
}