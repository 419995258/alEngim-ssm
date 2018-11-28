package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="org.zyyd.base.entity.BasePermission")
public class BasePermission implements Serializable {
    /* */
    @ApiModelProperty(value="")
    private String permissionId;

    /* 权限名*/
    @ApiModelProperty(value="权限名")
    private String permissionName;

    /* 权限code*/
    @ApiModelProperty(value="权限code")
    private String code;

    /* 父code*/
    @ApiModelProperty(value="父code")
    private String parentCode;

    /* 权限地址*/
    @ApiModelProperty(value="权限地址")
    private String permissionUrl;

    /* 描述*/
    @ApiModelProperty(value="描述")
    private String permissionDesc;

    /* 创建人*/
    @ApiModelProperty(value="创建人")
    private String creUser;

    /* 修改人*/
    @ApiModelProperty(value="修改人")
    private String modUser;

    /* 创建时间*/
    @ApiModelProperty(value="创建时间")
    private Date creTime;

    /* 修改时间*/
    @ApiModelProperty(value="修改时间")
    private Date modTime;

    /* 删除状态：0正常，1删除*/
    @ApiModelProperty(value="删除状态：0正常，1删除")
    private Integer deleteFlag;

    private static final long serialVersionUID = 1L;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}