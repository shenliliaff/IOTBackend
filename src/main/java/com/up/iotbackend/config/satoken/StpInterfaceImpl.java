package com.up.iotbackend.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
//    @Resource
//    private PermissionsMapper permissionsMapper;
//    @Resource
//    private UserRoleMapper userRoleMapper;
//    @Resource
//    private TblRoleInfoMapper roleMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();
//        SaSession session = StpUtil.getSessionByLoginId(loginId);
//        return session.get(SaConst.PERMISSIONS__ID,()->{
//            List<String> roleList = getRoleList(loginId, loginType);
//
//            session.set(SaConst.ROLE__ID,roleList);
//
//            List<Role> roleInfos = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getId, roleList));
//            List<String> roleCodes = roleInfos.stream().map(Role::getRolesCode).collect(Collectors.toList());
//            session.set(SaConst.ROLE__CODE,roleCodes);
//
//            ArrayList<String> permissions = new ArrayList<>();
//            roleList.forEach(roleId-> permissions.addAll(permissionsMapper.selectPermissionsByRoleId(roleId)));
//            return permissions.stream().distinct().collect(Collectors.toList());
//        });
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return new ArrayList<>();
//        SaSession session = StpUtil.getSessionByLoginId(loginId);
//        return session.get(SaConst.ROLE__ID, () -> {
//            QueryWrapper<FmsSysUserRole> wrapper = new QueryWrapper<FmsSysUserRole>()
//                    .select("ROLE_ID")
//                    .eq("USER_ID", loginId);
//            List<FmsSysUserRole> roles = userRoleMapper.selectList(wrapper);
//
//            return roles.stream().map(FmsSysUserRole::getRoleId).collect(Collectors.toList());
//        });
    }
}
