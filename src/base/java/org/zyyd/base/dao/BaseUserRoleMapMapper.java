package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseUserRoleMap;
import org.zyyd.base.entity.BaseUserRoleMapExample;

public interface BaseUserRoleMapMapper {
    long countByExample(BaseUserRoleMapExample example);

    int deleteByExample(BaseUserRoleMapExample example);

    int deleteByPrimaryKey(String mapId);

    int insert(BaseUserRoleMap record);

    int insertSelective(BaseUserRoleMap record);

    List<BaseUserRoleMap> selectByExample(BaseUserRoleMapExample example);

    BaseUserRoleMap selectByPrimaryKey(String mapId);

    int updateByExampleSelective(@Param("record") BaseUserRoleMap record, @Param("example") BaseUserRoleMapExample example);

    int updateByExample(@Param("record") BaseUserRoleMap record, @Param("example") BaseUserRoleMapExample example);

    int updateByPrimaryKeySelective(BaseUserRoleMap record);

    int updateByPrimaryKey(BaseUserRoleMap record);
}