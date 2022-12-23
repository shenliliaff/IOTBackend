package com.up.iotbackend.service;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.TblUser;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-15
 */
public interface ITblUserService extends IService<TblUser> {

    List<TblUser> getAllUsers();
    String getNameById(Integer id);

    void deleteUserById(Integer id);

    void insertUser(TblUser user);

    ResultData updateUser(TblUser user);
}
