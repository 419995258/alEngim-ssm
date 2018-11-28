package org.zyyd.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="org.zyyd.base.entity.BaseRolePermissionMap")
public class BaseRolePermissionMap implements Serializable {
    /**
	* 
	*/
    @ApiModelProperty(value="")
    private String mapId;

    /**
	* 角色
	*/
    @ApiModelProperty(value="角色")
    private String roleCode;

    /**
	* 权限
	*/
    @ApiModelProperty(value="权限")
    private String permissionCode;

    private static final long serialVersionUID = 1L;

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}