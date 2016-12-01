package com.xuxl.mybatis.service;

import com.xuxl.mybatis.entities.RefundRequisitionDetail;
import com.xuxl.mybatis.entities.RefundRequisitionDetailCriteria;
import com.xuxl.mybatis.mapper.RefundRequisitionDetailMapper;
import com.xuxl.mybatis.service.common.AbstractCommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefundRequisitionDetailServiceImpl extends AbstractCommonServiceImpl<RefundRequisitionDetailMapper, RefundRequisitionDetail, RefundRequisitionDetailCriteria, Long> implements RefundRequisitionDetailService {
    
	@Autowired
    private RefundRequisitionDetailMapper refundRequisitionDetailMapper;

    protected RefundRequisitionDetailMapper getMapper() {
        return refundRequisitionDetailMapper;
    }

    protected RefundRequisitionDetailCriteria getCriteria() {
        return new RefundRequisitionDetailCriteria();
    }
}