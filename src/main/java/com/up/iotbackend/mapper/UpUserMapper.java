package com.up.iotbackend.mapper;

import cn.hutool.core.date.DateTime;
import com.up.iotbackend.entity.UpUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * up用户信息表 Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-25
 */
public interface UpUserMapper extends BaseMapper<UpUser> {
    @Select("select * from up_user where is_in_use = 1 and venue_id = #{venueId}")
    List<UpUser> getUsersByVenue(Integer venueId);

    @Select("select * from up_user where is_in_use = 1 and id = #{userId}")
    List<UpUser> getUserByUserId(Integer userId);

    @Select("select * from up_user where is_in_use = 1 and open_id = #{openId}")
    List<UpUser> getUserByOpenId(String openId);

    @Select("select * from up_user where is_in_use = 1 and phone = #{phone}")
    List<UpUser> getUserByPhone(String phone);

    @Insert("insert into up_user (user_name,phone,venue_id,is_in_use,creator,modifier,open_id,token) value (#{userName},#{phone},#{venueId},1,#{creator},#{creator},#{openId},#{token})")
    void insertUser(String userName,String phone,Integer venueId,String creator,String openId,String token);

    @Update("update up_user set update_time=#{updateTime} where is_in_use = 1 and open_id=#{openId}")
    void updateUserTime(DateTime updateTime,String openId);

    @Update("update up_user set token=#{token},update_time=#{updateTime} where is_in_use = 1 and phone=#{phone}")
    void updateUserToken(String token,String phone,DateTime updateTime);

}
