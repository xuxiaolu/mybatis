package com.xuxl.mybatis.service;

import com.xuxl.mybatis.entities.RefundRequisition;
import com.xuxl.mybatis.entities.RefundRequisitionCriteria;
import com.xuxl.mybatis.mapper.RefundRequisitionMapper;
import com.xuxl.mybatis.service.common.AbstractCommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefundRequisitionServiceImpl extends AbstractCommonServiceImpl<RefundRequisitionMapper, RefundRequisition, RefundRequisitionCriteria, Long> implements RefundRequisitionService {
    
	@Autowired
    private RefundRequisitionMapper refundRequisitionMapper;

    protected RefundRequisitionMapper getMapper() {
        return refundRequisitionMapper;
    }

    protected RefundRequisitionCriteria getCriteria() {
        return new RefundRequisitionCriteria();
    }
}