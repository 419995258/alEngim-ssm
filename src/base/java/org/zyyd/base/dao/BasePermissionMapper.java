package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BasePermission;
import org.zyyd.base.entity.BasePermissionExample;

public interface BasePermissionMapper {
    long countByExample(BasePermissionExample example);

    int deleteByExample(BasePermissionExample example);

    int deleteByPrimaryKey(String permissionId);

    int insert(BasePermission record);

    int insertSelective(BasePermission record);

    List<BasePermission> selectByExample(BasePermissionExample example);

    BasePermission selectByPrimaryKey(String permissionId);

    int updateByExampleSelective(@Param("record") BasePermission record, @Param("example") BasePermissionExample example);

    int updateByExample(@Param("record") BasePermission record, @Param("example") BasePermissionExample example);

    int updateByPrimaryKeySelective(BasePermission record);

    int updateByPrimaryKey(BasePermission record);
}