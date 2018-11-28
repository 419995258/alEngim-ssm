package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.entity.BasePropertiesExample;

public interface BasePropertiesMapper {
    long countByExample(BasePropertiesExample example);

    int deleteByExample(BasePropertiesExample example);

    int deleteByPrimaryKey(String pid);

    int insert(BaseProperties record);

    int insertSelective(BaseProperties record);

    List<BaseProperties> selectByExample(BasePropertiesExample example);

    BaseProperties selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") BaseProperties record, @Param("example") BasePropertiesExample example);

    int updateByExample(@Param("record") BaseProperties record, @Param("example") BasePropertiesExample example);

    int updateByPrimaryKeySelective(BaseProperties record);

    int updateByPrimaryKey(BaseProperties record);

    List<BaseProperties> selectByExampleWithBLOBs(BasePropertiesExample example);

    int updateByExampleWithBLOBs(@Param("record") BaseProperties record, @Param("example") BasePropertiesExample example);

    int updateByPrimaryKeyWithBLOBs(BaseProperties record);
}