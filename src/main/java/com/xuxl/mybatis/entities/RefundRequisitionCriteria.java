package com.xuxl.mybatis.entities;

import com.xuxl.mybatis.entities.common.AbstractCriteria;
import com.xuxl.mybatis.entities.common.ColumnType;
import java.math.BigDecimal;
import java.util.Date;

public class RefundRequisitionCriteria extends AbstractCriteria {

    public RefundRequisitionCriteria() {
        super();
        propertyMap.put("id",new ColumnType("id",Long.class));
        propertyMap.put("requisitionNo",new ColumnType("requisition_no",String.class));
        propertyMap.put("status",new ColumnType("status",Integer.class));
        propertyMap.put("sumReturnAmount",new ColumnType("sum_return_amount",BigDecimal.class));
        propertyMap.put("canReturnAmount",new ColumnType("can_return_amount",BigDecimal.class));
        propertyMap.put("factorage",new ColumnType("factorage",BigDecimal.class));
        propertyMap.put("feeRate",new ColumnType("fee_rate",BigDecimal.class));
        propertyMap.put("customerLoginName",new ColumnType("customer_login_name",String.class));
        propertyMap.put("createTime",new ColumnType("create_time",Date.class));
        propertyMap.put("modifyTime",new ColumnType("modify_time",Date.class));
        propertyMap.put("creator",new ColumnType("creator",Integer.class));
        propertyMap.put("creatorName",new ColumnType("creator_name",String.class));
        propertyMap.put("modifier",new ColumnType("modifier",Integer.class));
        propertyMap.put("refundNo",new ColumnType("refund_no",String.class));
        propertyMap.put("refundCreateTime",new ColumnType("refund_create_time",Date.class));
        propertyMap.put("refundModifyTime",new ColumnType("refund_modify_time",Date.class));
        propertyMap.put("remarks",new ColumnType("remarks",String.class));
        propertyMap.put("version",new ColumnType("version",Integer.class));
    }
}