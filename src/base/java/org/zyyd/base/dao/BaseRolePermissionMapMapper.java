package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseRolePermissionMap;
import org.zyyd.base.entity.BaseRolePermissionMapExample;

public interface BaseRolePermissionMapMapper {
    long countByExample(BaseRolePermissionMapExample example);

    int deleteByExample(BaseRolePermissionMapExample example);

    int deleteByPrimaryKey(String mapId);

    int insert(BaseRolePermissionMap record);

    int insertSelective(BaseRolePermissionMap record);

    List<BaseRolePermissionMap> selectByExample(BaseRolePermissionMapExample example);

    BaseRolePermissionMap selectByPrimaryKey(String mapId);

    int updateByExampleSelective(@Param("record") BaseRolePermissionMap record, @Param("example") BaseRolePermissionMapExample example);

    int updateByExample(@Param("record") BaseRolePermissionMap record, @Param("example") BaseRolePermissionMapExample example);

    int updateByPrimaryKeySelective(BaseRolePermissionMap record);

    int updateByPrimaryKey(BaseRolePermissionMap record);
}