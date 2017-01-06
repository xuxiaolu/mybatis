package com.xuxl.mybatis.mapper.common;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuxl.mybatis.entities.common.AbstractCriteria;
import com.xuxl.mybatis.entities.common.BaseEntity;


public interface Mapper<T extends BaseEntity<PK>,A extends AbstractCriteria,PK extends Serializable> {
	
	PK countByExample(A example);

    int deleteByExample(A example);

    int deleteByPrimaryKey(PK id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(A example);

    T selectByPrimaryKey(PK id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") A example);

    int updateByExample(@Param("record") T record, @Param("example") A example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
}
