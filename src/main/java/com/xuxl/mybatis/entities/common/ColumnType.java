package com.xuxl.mybatis.entities.common;

public class ColumnType {
	
	private String column;
	
	private Class<?> type;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public ColumnType(String column, Class<?> type) {
		this.column = column;
		this.type = type;
	}
}

