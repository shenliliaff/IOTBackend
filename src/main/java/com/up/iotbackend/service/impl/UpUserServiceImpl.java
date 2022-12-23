package com.up.iotbackend.service.impl;

import com.up.iotbackend.entity.UpUser;
import com.up.iotbackend.mapper.UpUserMapper;
import com.up.iotbackend.service.IUpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * up用户信息表 服务实现类
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@Service
public class UpUserServiceImpl extends ServiceImpl<UpUserMapper, UpUser> implements IUpUserService {

}
