package com.xuxl.mybatis.service.common;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xuxl.mybatis.entities.common.AbstractCriteria;
import com.xuxl.mybatis.entities.common.BaseEntity;


public interface CommonService<T extends BaseEntity<PK>,A extends AbstractCriteria,PK extends Serializable> {

	public int insert(T entity);

    public int insertSelective(T entity);

    public PK count(A criteria);

    public int deleteAll(A example);

    public int delete(PK id);

    public List<T> list(A criteria);

    public PageInfo<T> page(A criteria,int pageNum,int pageSize);

    public T get(PK id);

    public int updateByExampleSelective(T entity, A criteria);

    public int updateByExample(T entity, A criteria);

    public int updateByPrimaryKeySelective(T entity);

    public int updateByPrimaryKey(T entity);
    
}
