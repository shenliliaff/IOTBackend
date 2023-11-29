package com.up.iotbackend.mapper;

import com.up.iotbackend.entity.UpUserRoleRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * up用户角色关系信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
public interface UpUserRoleRefMapper extends BaseMapper<UpUserRoleRef> {
    //获取所有可选的某个venueId 的sysadmin
    @Select("select * from up_user_role_ref r " +
            "left join up_user u on r.user_id=u.id " +
            "left join up_role t on r.role_id=t.id " +
            "where u.venue_id = #{venueId} and r.is_in_use = 1 and u.is_in_use = 1 and t.is_in_use = 1 and t.role_name = #{roleName}")
    List<Map<String,Object>> getAllUsersByRoleName(Integer venueId, String roleName);
}
