package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="org.zyyd.base.entity.BaseRole")
public class BaseRole implements Serializable {
    /* 主键*/
    @ApiModelProperty(value="主键")
    private String roleId;

    /* 角色名称*/
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /* 角色代码*/
    @ApiModelProperty(value="角色代码")
    private String roleCode;

    /* 角色类型，以后也许用得上*/
    @ApiModelProperty(value="角色类型，以后也许用得上")
    private String roleType;

    /* 该身份登陆后的默认url*/
    @ApiModelProperty(value="该身份登陆后的默认url")
    private String defaultUrl;

    /* 创建时间*/
    @ApiModelProperty(value="创建时间")
    private Date creTime;

    /* 创建人*/
    @ApiModelProperty(value="创建人")
    private String creUser;

    /* 修改时间*/
    @ApiModelProperty(value="修改时间")
    private Date modTime;

    /* 修改人*/
    @ApiModelProperty(value="修改人")
    private String modUser;

    /* 角色描述*/
    @ApiModelProperty(value="角色描述")
    private String roleDesc;

    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}