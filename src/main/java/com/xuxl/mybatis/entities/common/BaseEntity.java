package com.xuxl.mybatis.entities.common;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<PK> implements Serializable {

	private static final long serialVersionUID = 1L;
	
    public final static Integer DELETE = 1;
    
    public final static Integer NORMAL = 0;
    
    public final static Integer DEFAULT_VERSION = 1;

	protected PK id;

	protected Date createTime;

	protected Date modifyTime;

	protected Integer creator;

	protected Integer modifier;

	protected Integer version;

	protected Integer status;
	
	protected Integer isDelete;
	
	public BaseEntity() {
		this.isDelete = NORMAL;
		this.version = DEFAULT_VERSION;
		this.createTime = new Date();
		this.modifyTime = new Date();
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseEntity [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", modifyTime=");
		builder.append(modifyTime);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append(", version=");
		builder.append(version);
		builder.append(", status=");
		builder.append(status);
		builder.append(", isDelete=");
		builder.append(isDelete);
		builder.append("]");
		return builder.toString();
	}

	

}
