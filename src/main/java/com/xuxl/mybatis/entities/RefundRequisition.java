package com.xuxl.mybatis.entities;

import com.xuxl.mybatis.entities.common.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RefundRequisition extends BaseEntity<Long> implements Serializable {
    private String requisitionNo;

    private BigDecimal sumReturnAmount;

    private BigDecimal canReturnAmount;

    private BigDecimal factorage;

    private BigDecimal feeRate;

    private String customerLoginName;

    private String creatorName;

    private String refundNo;

    private Date refundCreateTime;

    private Date refundModifyTime;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public String getRequisitionNo() {
        return requisitionNo;
    }

    public void setRequisitionNo(String requisitionNo) {
        this.requisitionNo = requisitionNo == null ? null : requisitionNo.trim();
    }

    public BigDecimal getSumReturnAmount() {
        return sumReturnAmount;
    }

    public void setSumReturnAmount(BigDecimal sumReturnAmount) {
        this.sumReturnAmount = sumReturnAmount;
    }

    public BigDecimal getCanReturnAmount() {
        return canReturnAmount;
    }

    public void setCanReturnAmount(BigDecimal canReturnAmount) {
        this.canReturnAmount = canReturnAmount;
    }

    public BigDecimal getFactorage() {
        return factorage;
    }

    public void setFactorage(BigDecimal factorage) {
        this.factorage = factorage;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public String getCustomerLoginName() {
        return customerLoginName;
    }

    public void setCustomerLoginName(String customerLoginName) {
        this.customerLoginName = customerLoginName == null ? null : customerLoginName.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    public Date getRefundCreateTime() {
        return refundCreateTime;
    }

    public void setRefundCreateTime(Date refundCreateTime) {
        this.refundCreateTime = refundCreateTime;
    }

    public Date getRefundModifyTime() {
        return refundModifyTime;
    }

    public void setRefundModifyTime(Date refundModifyTime) {
        this.refundModifyTime = refundModifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", requisitionNo=").append(requisitionNo);
        sb.append(", sumReturnAmount=").append(sumReturnAmount);
        sb.append(", canReturnAmount=").append(canReturnAmount);
        sb.append(", factorage=").append(factorage);
        sb.append(", feeRate=").append(feeRate);
        sb.append(", customerLoginName=").append(customerLoginName);
        sb.append(", creatorName=").append(creatorName);
        sb.append(", refundNo=").append(refundNo);
        sb.append(", refundCreateTime=").append(refundCreateTime);
        sb.append(", refundModifyTime=").append(refundModifyTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        sb.append(", from super class ");
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RefundRequisition other = (RefundRequisition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRequisitionNo() == null ? other.getRequisitionNo() == null : this.getRequisitionNo().equals(other.getRequisitionNo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSumReturnAmount() == null ? other.getSumReturnAmount() == null : this.getSumReturnAmount().equals(other.getSumReturnAmount()))
            && (this.getCanReturnAmount() == null ? other.getCanReturnAmount() == null : this.getCanReturnAmount().equals(other.getCanReturnAmount()))
            && (this.getFactorage() == null ? other.getFactorage() == null : this.getFactorage().equals(other.getFactorage()))
            && (this.getFeeRate() == null ? other.getFeeRate() == null : this.getFeeRate().equals(other.getFeeRate()))
            && (this.getCustomerLoginName() == null ? other.getCustomerLoginName() == null : this.getCustomerLoginName().equals(other.getCustomerLoginName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreatorName() == null ? other.getCreatorName() == null : this.getCreatorName().equals(other.getCreatorName()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getRefundNo() == null ? other.getRefundNo() == null : this.getRefundNo().equals(other.getRefundNo()))
            && (this.getRefundCreateTime() == null ? other.getRefundCreateTime() == null : this.getRefundCreateTime().equals(other.getRefundCreateTime()))
            && (this.getRefundModifyTime() == null ? other.getRefundModifyTime() == null : this.getRefundModifyTime().equals(other.getRefundModifyTime()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRequisitionNo() == null) ? 0 : getRequisitionNo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSumReturnAmount() == null) ? 0 : getSumReturnAmount().hashCode());
        result = prime * result + ((getCanReturnAmount() == null) ? 0 : getCanReturnAmount().hashCode());
        result = prime * result + ((getFactorage() == null) ? 0 : getFactorage().hashCode());
        result = prime * result + ((getFeeRate() == null) ? 0 : getFeeRate().hashCode());
        result = prime * result + ((getCustomerLoginName() == null) ? 0 : getCustomerLoginName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreatorName() == null) ? 0 : getCreatorName().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getRefundNo() == null) ? 0 : getRefundNo().hashCode());
        result = prime * result + ((getRefundCreateTime() == null) ? 0 : getRefundCreateTime().hashCode());
        result = prime * result + ((getRefundModifyTime() == null) ? 0 : getRefundModifyTime().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}