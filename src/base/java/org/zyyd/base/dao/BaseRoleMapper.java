package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseRole;
import org.zyyd.base.entity.BaseRoleExample;

public interface BaseRoleMapper {
    long countByExample(BaseRoleExample example);

    int deleteByExample(BaseRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(BaseRole record);

    int insertSelective(BaseRole record);

    List<BaseRole> selectByExampleWithBLOBs(BaseRoleExample example);

    List<BaseRole> selectByExample(BaseRoleExample example);

    BaseRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") BaseRole record, @Param("example") BaseRoleExample example);

    int updateByExampleWithBLOBs(@Param("record") BaseRole record, @Param("example") BaseRoleExample example);

    int updateByExample(@Param("record") BaseRole record, @Param("example") BaseRoleExample example);

    int updateByPrimaryKeySelective(BaseRole record);

    int updateByPrimaryKeyWithBLOBs(BaseRole record);

    int updateByPrimaryKey(BaseRole record);
}