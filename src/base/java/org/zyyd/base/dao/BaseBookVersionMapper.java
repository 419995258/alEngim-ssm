package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseBookVersion;
import org.zyyd.base.entity.BaseBookVersionExample;

public interface BaseBookVersionMapper {
    long countByExample(BaseBookVersionExample example);

    int deleteByExample(BaseBookVersionExample example);

    int deleteByPrimaryKey(String versionId);

    int insert(BaseBookVersion record);

    int insertSelective(BaseBookVersion record);

    List<BaseBookVersion> selectByExample(BaseBookVersionExample example);

    BaseBookVersion selectByPrimaryKey(String versionId);

    int updateByExampleSelective(@Param("record") BaseBookVersion record, @Param("example") BaseBookVersionExample example);

    int updateByExample(@Param("record") BaseBookVersion record, @Param("example") BaseBookVersionExample example);

    int updateByPrimaryKeySelective(BaseBookVersion record);

    int updateByPrimaryKey(BaseBookVersion record);
}