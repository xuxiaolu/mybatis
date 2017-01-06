package com.xuxl.mybatis.service.common;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuxl.mybatis.entities.common.AbstractCriteria;
import com.xuxl.mybatis.entities.common.AbstractCriteria.Criteria;
import com.xuxl.mybatis.entities.common.BaseEntity;
import com.xuxl.mybatis.mapper.common.Mapper;


public abstract class AbstractCommonServiceImpl<D extends Mapper<T, A, PK>, T extends BaseEntity<PK>, A extends AbstractCriteria,PK extends Serializable> implements CommonService<T, A, PK> {

    protected abstract D getMapper();
    
    protected abstract A getCriteria();

    public int insert(T entity) {
        return getMapper().insert(entity);
    }

    public int insertSelective(T entity) {
        return getMapper().insertSelective(entity);
    }

    public PK count(A criteria) {
        return getMapper().countByExample(criteria);
    }
    
    public int deleteAll(A example) {
        List<T> entities = list(example);
        int result = 0;
        for (T entity : entities) {
            entity.setIsDelete(BaseEntity.DELETE);
            entity.setVersion(entity.getVersion() + 1);
            result += getMapper().updateByPrimaryKeySelective(entity);
        }
        return result;
    }

    public int delete(PK id) {
        T entity = get(id);
        if (entity == null) return 0;
        entity.setIsDelete(T.DELETE);
        entity.setVersion(entity.getVersion() + 1);
        A criteria = getCriteria();
        criteria.createCriteria().andEqualTo("id",id).andEqualTo("version",entity.getVersion());
        return getMapper().updateByExampleSelective(entity, criteria);
    }

    public List<T> list(A criteria) {
        return getMapper().selectByExample(criteria);
    }

    public PageInfo<T> page(A criteria,int pageNum,int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
        List<T> list = getMapper().selectByExample(criteria);
        return new PageInfo<>(list);
    }

    public T get(PK id) {
        return getMapper().selectByPrimaryKey(id);
    }
    
    public int updateByExampleSelective(T entity, A criteria) {
    	Integer version = entity.getVersion();
    	List<Criteria> criteriaList = criteria.getOredCriteria();
    	criteriaList.stream().forEach(c -> c.andEqualTo("version", version));
    	entity.setVersion(version + 1);
    	return getMapper().updateByExampleSelective(entity, criteria);
    }

    public int updateByExample(T entity, A criteria) {
    	Integer version = entity.getVersion();
    	List<Criteria> criteriaList = criteria.getOredCriteria();
    	criteriaList.stream().forEach(c -> c.andEqualTo("version", version));
    	entity.setVersion(version + 1);
    	return getMapper().updateByExample(entity, criteria);
    }

    public int updateByPrimaryKeySelective(T entity) {
    	return getMapper().updateByPrimaryKeySelective(entity);
    }

    public int updateByPrimaryKey(T entity) {
    	return getMapper().updateByPrimaryKeySelective(entity);
    }
    
}

