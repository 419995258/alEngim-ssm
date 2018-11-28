package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BasePropertiesGroup;
import org.zyyd.base.entity.BasePropertiesGroupExample;

public interface BasePropertiesGroupMapper {
    long countByExample(BasePropertiesGroupExample example);

    int deleteByExample(BasePropertiesGroupExample example);

    int deleteByPrimaryKey(String gid);

    int insert(BasePropertiesGroup record);

    int insertSelective(BasePropertiesGroup record);

    List<BasePropertiesGroup> selectByExample(BasePropertiesGroupExample example);

    BasePropertiesGroup selectByPrimaryKey(String gid);

    int updateByExampleSelective(@Param("record") BasePropertiesGroup record, @Param("example") BasePropertiesGroupExample example);

    int updateByExample(@Param("record") BasePropertiesGroup record, @Param("example") BasePropertiesGroupExample example);

    int updateByPrimaryKeySelective(BasePropertiesGroup record);

    int updateByPrimaryKey(BasePropertiesGroup record);
}