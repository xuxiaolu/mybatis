package com.xuxl.mybatis.entities.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCriteria {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected Map<String, ColumnType> propertyMap;
	
	protected AbstractCriteria() {
		oredCriteria = new ArrayList<>();
		propertyMap = new HashMap<>();	
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}
	
	public class Criteria {

		protected List<Criterion> criteria;

		public Criteria() {
			criteria = new ArrayList<>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}
		
		private String column(String property) {
			if (propertyMap.containsKey(property)) {
				return propertyMap.get(property).getColumn();
			} else {
				throw new RuntimeException("当前实体类不包含名为" + property + "的属性!");
			}
		}

		private String column(String property, Object... object) {
			if (propertyMap.containsKey(property)) {
				Class<?> type = propertyMap.get(property).getType();
				if (object.length == 1) {
					if (object.getClass() != type) {
						throw new RuntimeException(property + "属性的类型不正确,应该是" + type + ",不是" + object.getClass() + "!");
					}
				} else {
					String[] classes = Arrays.asList(object).stream().map(Object :: getClass).filter(clazz -> clazz != type)
							.map(clazz -> clazz.getName()).toArray(String[]::new);
					if (classes.length > 0) {
						throw new RuntimeException(
								property + "属性的类型不正确,应该是" + type + ",不是" + Arrays.toString(classes) + "!");
					}
				}
				return propertyMap.get(property).getColumn();
			} else {
				throw new RuntimeException("当前实体类不包含名为" + property + "的属性!");
			}
		}

		private String property(String property) {
			if (propertyMap.containsKey(property)) {
				return property;
			} else {
				throw new RuntimeException("当前实体类不包含名为" + property + "的属性!");
			}
		}
		

		public Criteria andIsNull(String property) {
			addCriterion(column(property) + " is null");
			return (Criteria) this;
		}

		public Criteria andIsNotNull(String property) {
			addCriterion(column(property) + " is not null");
			return (Criteria) this;
		}

		public Criteria andEqualTo(String property, Object value) {
			addCriterion(column(property) + " =", value, property(property));
			return (Criteria) this;
		}

		public Criteria andNotEqualTo(String property, Object value) {
			addCriterion(column(property) + " <>", value, property(property));
			return (Criteria) this;
		}

		public Criteria andGreaterThan(String property, Object value) {
			addCriterion(column(property, value) + " >", value, property(property));
			return (Criteria) this;
		}

		public Criteria andGreaterThanOrEqualTo(String property, Object value) {
			addCriterion(column(property, value) + " >=", value, property(property));
			return (Criteria) this;
		}

		public Criteria andLessThan(String property, Object value) {
			addCriterion(column(property, value) + " <", value, property(property));
			return (Criteria) this;
		}

		public Criteria andLessThanOrEqualTo(String property, Object value) {
			addCriterion(column(property, value) + " <=", value, property(property));
			return (Criteria) this;
		}

		public Criteria andIn(String property, List<?> values) {
			addCriterion(column(property, values.toArray()) + " in", values, property(property));
			return (Criteria) this;
		}

		public Criteria andNotIn(String property, List<?> values) {
			addCriterion(column(property, values.toArray()) + " not in", values, property(property));
			return (Criteria) this;
		}

		public Criteria andBetween(String property, Object value1, Object value2) {
			addCriterion(column(property, value1, value2) + " between", value1, value2, property(property));
			return (Criteria) this;
		}

		public Criteria andNotBetween(String property, Object value1, Object value2) {
			addCriterion(column(property, value1, value2) + " not between", value1, value2, property(property));
			return (Criteria) this;
		}

		public Criteria andLike(String property, String value) {
			addCriterion(column(property) + "  like", value, property(property));
			return (Criteria) this;
		}

		public Criteria andNotLike(String property, String value) {
			addCriterion(column(property) + "  not like", value, property(property));
			return (Criteria) this;
		}

	}
}