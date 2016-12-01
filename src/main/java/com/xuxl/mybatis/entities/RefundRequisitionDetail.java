package com.xuxl.mybatis.entities;

import com.xuxl.mybatis.entities.common.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;

public class RefundRequisitionDetail extends BaseEntity<Long> implements Serializable {
    private String requisitionNo;

    private String cardNo;

    private BigDecimal cardDenomination;

    private BigDecimal cardBalance;

    private BigDecimal cardFactorage;

    private BigDecimal cardReturnAmount;

    private String cardType;

    private String cardStatus;

    private static final long serialVersionUID = 1L;

    public String getRequisitionNo() {
        return requisitionNo;
    }

    public void setRequisitionNo(String requisitionNo) {
        this.requisitionNo = requisitionNo == null ? null : requisitionNo.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public BigDecimal getCardDenomination() {
        return cardDenomination;
    }

    public void setCardDenomination(BigDecimal cardDenomination) {
        this.cardDenomination = cardDenomination;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public BigDecimal getCardFactorage() {
        return cardFactorage;
    }

    public void setCardFactorage(BigDecimal cardFactorage) {
        this.cardFactorage = cardFactorage;
    }

    public BigDecimal getCardReturnAmount() {
        return cardReturnAmount;
    }

    public void setCardReturnAmount(BigDecimal cardReturnAmount) {
        this.cardReturnAmount = cardReturnAmount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", requisitionNo=").append(requisitionNo);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", cardDenomination=").append(cardDenomination);
        sb.append(", cardBalance=").append(cardBalance);
        sb.append(", cardFactorage=").append(cardFactorage);
        sb.append(", cardReturnAmount=").append(cardReturnAmount);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardStatus=").append(cardStatus);
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
        RefundRequisitionDetail other = (RefundRequisitionDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRequisitionNo() == null ? other.getRequisitionNo() == null : this.getRequisitionNo().equals(other.getRequisitionNo()))
            && (this.getCardNo() == null ? other.getCardNo() == null : this.getCardNo().equals(other.getCardNo()))
            && (this.getCardDenomination() == null ? other.getCardDenomination() == null : this.getCardDenomination().equals(other.getCardDenomination()))
            && (this.getCardBalance() == null ? other.getCardBalance() == null : this.getCardBalance().equals(other.getCardBalance()))
            && (this.getCardFactorage() == null ? other.getCardFactorage() == null : this.getCardFactorage().equals(other.getCardFactorage()))
            && (this.getCardReturnAmount() == null ? other.getCardReturnAmount() == null : this.getCardReturnAmount().equals(other.getCardReturnAmount()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getCardStatus() == null ? other.getCardStatus() == null : this.getCardStatus().equals(other.getCardStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRequisitionNo() == null) ? 0 : getRequisitionNo().hashCode());
        result = prime * result + ((getCardNo() == null) ? 0 : getCardNo().hashCode());
        result = prime * result + ((getCardDenomination() == null) ? 0 : getCardDenomination().hashCode());
        result = prime * result + ((getCardBalance() == null) ? 0 : getCardBalance().hashCode());
        result = prime * result + ((getCardFactorage() == null) ? 0 : getCardFactorage().hashCode());
        result = prime * result + ((getCardReturnAmount() == null) ? 0 : getCardReturnAmount().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getCardStatus() == null) ? 0 : getCardStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}