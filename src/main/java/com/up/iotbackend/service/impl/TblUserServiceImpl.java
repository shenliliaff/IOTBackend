package com.up.iotbackend.service.impl;

import com.up.iotbackend.entity.ResultData;
import com.up.iotbackend.entity.ResultEnum;
import com.up.iotbackend.entity.TblUser;
import com.up.iotbackend.mapper.TblUserMapper;
import com.up.iotbackend.service.ITblUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-15
 */
@Service
public class TblUserServiceImpl extends ServiceImpl<TblUserMapper, TblUser> implements ITblUserService {

    @Resource
    private TblUserMapper userMapper;

    @Override
    public List<TblUser> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public String getNameById(Integer id) {
        return userMapper.getNameById(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void insertUser(TblUser user) {
        userMapper.insertUser(user);
    }

    @Override
    public ResultData updateUser(TblUser user) {
        userMapper.updateUser(user);
        return ResultData.success(user);
    }

}
