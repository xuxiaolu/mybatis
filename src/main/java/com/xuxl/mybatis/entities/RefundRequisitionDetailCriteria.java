package com.xuxl.mybatis.entities;

import com.xuxl.mybatis.entities.common.AbstractCriteria;
import com.xuxl.mybatis.entities.common.ColumnType;
import java.math.BigDecimal;
import java.util.Date;

public class RefundRequisitionDetailCriteria extends AbstractCriteria {

    public RefundRequisitionDetailCriteria() {
        super();
        propertyMap.put("id",new ColumnType("id",Long.class));
        propertyMap.put("requisitionNo",new ColumnType("requisition_no",String.class));
        propertyMap.put("cardNo",new ColumnType("card_no",String.class));
        propertyMap.put("cardDenomination",new ColumnType("card_denomination",BigDecimal.class));
        propertyMap.put("cardBalance",new ColumnType("card_balance",BigDecimal.class));
        propertyMap.put("cardFactorage",new ColumnType("card_factorage",BigDecimal.class));
        propertyMap.put("cardReturnAmount",new ColumnType("card_return_amount",BigDecimal.class));
        propertyMap.put("cardType",new ColumnType("card_type",String.class));
        propertyMap.put("cardStatus",new ColumnType("card_status",String.class));
        propertyMap.put("createTime",new ColumnType("create_time",Date.class));
        propertyMap.put("modifyTime",new ColumnType("modify_time",Date.class));
        propertyMap.put("creator",new ColumnType("creator",Integer.class));
        propertyMap.put("modifier",new ColumnType("modifier",Integer.class));
        propertyMap.put("version",new ColumnType("version",Integer.class));
    }
}