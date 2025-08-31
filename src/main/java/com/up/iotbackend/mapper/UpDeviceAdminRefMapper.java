package com.up.iotbackend.mapper;

import com.up.iotbackend.entity.UpDeviceAdminRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up设备管理员关系信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
public interface UpDeviceAdminRefMapper extends BaseMapper<UpDeviceAdminRef> {
    @Select("select r.device_sn,r.user_id,d.device_name,u.user_name from up_device_admin_ref r \n" +
            "left join up_device_info d on r.device_sn = d.device_sn\n" +
            "left join up_user u on u.id=r.user_id\n" +
            "where r.is_in_use = 1 and d.is_in_use = 1 and u.is_in_use = 1 and r.device_sn = #{deviceSn}")
    List<Map<String,Object>> getDeviceAdmins(String deviceSn);

    @Select("select * from up_device_admin_ref where is_in_use = 1 and device_sn = #{deviceSn} and user_id=#{userId}")
    List<UpDeviceAdminRef> getDeviceAdmin(String deviceSn,Integer userId);

    @Update("update up_device_admin_ref set user_id = #{userId},modifier = #{modifier} where is_in_use = 1 and id=#{deviceAdminRefId}")
    void updateAdmin(Integer userId,String deviceAdminRefId,String modifier);

    @Insert("insert into up_device_admin_ref (device_sn,user_id,device_admin_ref_name,is_in_use,creator,modifier) value" +
            "(#{deviceSn},#{userId},#{device_admin_ref_name},1,#{creator},#{creator})")
    void addDeviceAdmin(String deviceSn,Integer userId, String device_admin_ref_name,String creator);
}
