package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseKnowledgeCatelog;
import org.zyyd.base.entity.BaseKnowledgeCatelogExample;

public interface BaseKnowledgeCatelogMapper {
    long countByExample(BaseKnowledgeCatelogExample example);

    int deleteByExample(BaseKnowledgeCatelogExample example);

    int deleteByPrimaryKey(String kcId);

    int insert(BaseKnowledgeCatelog record);

    int insertSelective(BaseKnowledgeCatelog record);

    List<BaseKnowledgeCatelog> selectByExample(BaseKnowledgeCatelogExample example);

    BaseKnowledgeCatelog selectByPrimaryKey(String kcId);

    int updateByExampleSelective(@Param("record") BaseKnowledgeCatelog record, @Param("example") BaseKnowledgeCatelogExample example);

    int updateByExample(@Param("record") BaseKnowledgeCatelog record, @Param("example") BaseKnowledgeCatelogExample example);

    int updateByPrimaryKeySelective(BaseKnowledgeCatelog record);

    int updateByPrimaryKey(BaseKnowledgeCatelog record);
}