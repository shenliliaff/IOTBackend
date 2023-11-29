package com.up.iotbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jetbrains.annotations.Contract;

/**
 * <p>
 * up区域信息表
 * </p>
 *
 * @author Feng.An
 * @since 2022-12-18
 */
@TableName("up_location_info")
@ApiModel(value = "UpLocationInfo对象", description = "up区域信息表")
public class UpLocationInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    public UpLocationInfo(){}

    public UpLocationInfo(Integer id,String locationName,String remark){
        this.id=id;
        this.locationName=locationName;
        this.remark=remark;
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("区域名称")
    private String locationName;

    @ApiModelProperty("场馆id")
    private Integer venueId;

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
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
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
        return "UpLocationInfo{" +
            "id=" + id +
            ", locationName=" + locationName +
            ", venueId=" + venueId +
            ", isInUse=" + isInUse +
            ", creator=" + creator +
            ", createTime=" + createTime +
            ", modifier=" + modifier +
            ", updateTime=" + updateTime +
            ", remark=" + remark +
        "}";
    }
}
