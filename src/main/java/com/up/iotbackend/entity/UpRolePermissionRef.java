package com.up.iotbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * up角色权限关系信息表
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@TableName("up_role_permission_ref")
@ApiModel(value = "UpRolePermissionRef对象", description = "up角色权限关系信息表")
public class UpRolePermissionRef implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限id")
    private Integer permissionId;

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("角色权限关系名称")
    private String rolePermissionRefName;

    @ApiModelProperty("是否有效")
    private Integer isInUse;

    @ApiModelProperty("创建者")
    private String creator;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("用户名称")
    private String modifier;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("描述")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRolePermissionRefName() {
        return rolePermissionRefName;
    }

    public void setRolePermissionRefName(String rolePermissionRefName) {
        this.rolePermissionRefName = rolePermissionRefName;
    }
    public Integer getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(Integer isInUse) {
        this.isInUse = isInUse;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UpRolePermissionRef{" +
            "id=" + id +
            ", permissionId=" + permissionId +
            ", roleId=" + roleId +
            ", rolePermissionRefName=" + rolePermissionRefName +
            ", isInUse=" + isInUse +
            ", creator=" + creator +
            ", createTime=" + createTime +
            ", modifier=" + modifier +
            ", updateTime=" + updateTime +
            ", remark=" + remark +
        "}";
    }
}
