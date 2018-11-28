package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseArea;
import org.zyyd.base.entity.BaseAreaExample;

public interface BaseAreaMapper {
    long countByExample(BaseAreaExample example);

    int deleteByExample(BaseAreaExample example);

    int deleteByPrimaryKey(String areaId);

    int insert(BaseArea record);

    int insertSelective(BaseArea record);

    List<BaseArea> selectByExample(BaseAreaExample example);

    BaseArea selectByPrimaryKey(String areaId);

    int updateByExampleSelective(@Param("record") BaseArea record, @Param("example") BaseAreaExample example);

    int updateByExample(@Param("record") BaseArea record, @Param("example") BaseAreaExample example);

    int updateByPrimaryKeySelective(BaseArea record);

    int updateByPrimaryKey(BaseArea record);
}