package com.up.iotbackend.mapper;

import com.up.iotbackend.entity.TblUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-15
 */
public interface TblUserMapper extends BaseMapper<TblUser> {
    @Select("select * from tbl_user")
    @Results({
            @Result(property = "name", column = "name")
    })
    List<TblUser> getAllUsers();
    @Select("select * from tbl_user where id=#{id}")
    String getNameById(Integer id);

    @Delete("delete from tbl_user where id=#{id}")
    void deleteUserById(Integer id);

    @Insert("insert into tbl_user (id,name,age) values (#{id},#{name},#{age})")
    void insertUser(TblUser user);

    @Update("update tbl_user set name=#{name},age=#{age} where id=#{id}")
    void updateUser(TblUser user);
}
