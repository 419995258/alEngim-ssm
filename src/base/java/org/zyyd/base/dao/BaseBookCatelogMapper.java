package org.zyyd.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zyyd.base.entity.BaseBookCatelog;
import org.zyyd.base.entity.BaseBookCatelogExample;

public interface BaseBookCatelogMapper {
    long countByExample(BaseBookCatelogExample example);

    int deleteByExample(BaseBookCatelogExample example);

    int deleteByPrimaryKey(String bcId);

    int insert(BaseBookCatelog record);

    int insertSelective(BaseBookCatelog record);

    List<BaseBookCatelog> selectByExample(BaseBookCatelogExample example);

    BaseBookCatelog selectByPrimaryKey(String bcId);

    int updateByExampleSelective(@Param("record") BaseBookCatelog record, @Param("example") BaseBookCatelogExample example);

    int updateByExample(@Param("record") BaseBookCatelog record, @Param("example") BaseBookCatelogExample example);

    int updateByPrimaryKeySelective(BaseBookCatelog record);

    int updateByPrimaryKey(BaseBookCatelog record);
}