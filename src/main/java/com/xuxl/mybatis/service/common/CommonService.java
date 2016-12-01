package com.xuxl.mybatis.service.common;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xuxl.mybatis.entities.common.AbstractCriteria;
import com.xuxl.mybatis.entities.common.BaseEntity;


public interface CommonService<T extends BaseEntity<PK>,A extends AbstractCriteria,PK extends Serializable> {

    int insert(T entity);

    int insertSelective(T entity);

    PK count(A criteria);

    int deleteAll(A example);

    int delete(PK id);

    List<T> list(A criteria);

    List<T> list(A criteria,RowBounds rowBounds);

    T get(PK id);

    public int updateByExampleSelective(T entity, A criteria);

    public int updateByExample(T entity, A criteria);

    public int updateByPrimaryKeySelective(T entity);

    public int updateByPrimaryKey(T entity);
    
    
}
