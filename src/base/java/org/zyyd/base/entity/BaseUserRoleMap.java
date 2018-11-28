package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="org.zyyd.base.entity.BaseUserRoleMap")
public class BaseUserRoleMap implements Serializable {
    /* */
    @ApiModelProperty(value="")
    private String mapId;

    /* 用户id*/
    @ApiModelProperty(value="用户id")
    private String userId;

    /* 角色id*/
    @ApiModelProperty(value="角色id")
    private String roleId;

    private static final long serialVersionUID = 1L;

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}