package com.xuxl.mybatis.plugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.springframework.util.StringUtils;

public class IgnoreColumnsPlugin extends PluginAdapter {

	private static final String BASEENTITY = "com.xuxl.mybatis.entities.common.BaseEntity";

	private static final String ABSTRACTCRITERIA = "com.xuxl.mybatis.entities.common.AbstractCriteria";

	private static final String MAPPER = "com.xuxl.mybatis.mapper.common.Mapper";
	
	private static final String COLUMN_TYPE = "com.xuxl.mybatis.entities.common.ColumnType";

	private String column;

	private List<String> ignoreColumns;

	private String defaultPrimaryKey = "java.lang.Integer";

	private FullyQualifiedJavaType defaultPrimaryType = null;

	/**
	 * 是否生效
	 */
	public boolean validate(List<String> list) {
		this.column = this.properties.getProperty("columns");
		if (StringUtils.hasText(this.column)) {
			String[] columns = this.column.trim().split(",");
			this.ignoreColumns = Arrays.stream(columns).map(c -> c.trim()).collect(Collectors.toList());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 去掉UpdateByExampleSelective 的 id 更新
	 */
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
    	List<Element> elements = element.getElements();
    	XmlElement xmlElement = (XmlElement) elements.get(1);
    	List<Element> setElement = xmlElement.getElements();
    	setElement.remove(0);
        return true;
    }

    /**
     * 去掉UpdateByExample 的 id 更新
     */
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
    	List<Element> elements = element.getElements();
    	TextElement textElement = (TextElement) elements.get(1);
    	String content = textElement.getContent();
    	int index = content.indexOf(" ");
    	content = content.substring(0,index + 1);
    	try {
			java.lang.reflect.Field contentField = textElement.getClass().getDeclaredField("content");
			contentField.setAccessible(true);
			contentField.set(textElement, content);
			contentField.setAccessible(false);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return true;
    }

	public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		if (defaultPrimaryType == null) {
			buildPrimaryKeyType(introspectedTable);
		}
		for (Element e : element.getElements()) {
			XmlElement xmlElement = (XmlElement) e;
			if (xmlElement.getName().equals("id")) {
				Attribute attribute = new Attribute("javaType", defaultPrimaryType.getShortName());
				xmlElement.addAttribute(attribute);
			}
		}
		return true;
	}

	/**
	 * 实体类继承BaseEntity
	 */
	public boolean modelBaseRecordClassGenerated(TopLevelClass entityClass, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType baseEntity = new FullyQualifiedJavaType(BASEENTITY);
		if (this.defaultPrimaryType == null) {
			buildPrimaryKeyType(introspectedTable);
		}
		baseEntity.addTypeArgument(this.defaultPrimaryType);
		entityClass.setSuperClass(baseEntity.getShortName());
		entityClass.addImportedType(baseEntity);
		return true;
	}

	/**
	 * Criteria继承AbstractCriteria
	 */
	public boolean modelExampleClassGenerated(TopLevelClass exampleClass, IntrospectedTable introspectedTable) {
		Optional<Method> methodOptional = exampleClass.getMethods().stream()
				.filter(method -> method.getName().equals(exampleClass.getType().getShortName())).findFirst();
		Set<FullyQualifiedJavaType> importTypeSet = new HashSet<>();
		if (methodOptional.isPresent()) {
			Method initMethod = methodOptional.get();
			initMethod.getBodyLines().removeIf(line -> true);
			initMethod.addBodyLine("super();");
			introspectedTable.getAllColumns().stream().forEach(table -> {
				initMethod.addBodyLine(
						String.format("propertyMap.put(\"%s\",new ColumnType(\"%s\",%s.class));    //%s",
								table.getJavaProperty(), table.getActualColumnName(),
								table.getFullyQualifiedJavaType().getShortName(),table.getRemarks()));
				importTypeSet.add(table.getFullyQualifiedJavaType());
			});
		}
		try {
			java.lang.reflect.Field e = exampleClass.getClass().getDeclaredField("importedTypes");
			e.setAccessible(true);
			e.set(exampleClass, new HashSet<>());
			e.setAccessible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FullyQualifiedJavaType iCriteriaJavaType = new FullyQualifiedJavaType(ABSTRACTCRITERIA);
		exampleClass.setSuperClass(iCriteriaJavaType.getShortName());
		exampleClass.addImportedType(iCriteriaJavaType);
		exampleClass.getFields().removeIf(field -> true);
		exampleClass.getMethods().removeIf(method -> !method.getName().equals(exampleClass.getType().getShortName()));
		exampleClass.getInnerClasses().removeIf((innerClass) -> true);
		exampleClass.addImportedType(new FullyQualifiedJavaType(COLUMN_TYPE));
		exampleClass.addImportedTypes(importTypeSet);
		return true;
	}
	
	

	/**
	 * 生成mapper接口
	 */
	public boolean clientGenerated(Interface interfaze,TopLevelClass topLevelClass,IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(MAPPER);
		type.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		type.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
		if (this.defaultPrimaryType == null) {
			buildPrimaryKeyType(introspectedTable);
		}
		type.addTypeArgument(this.defaultPrimaryType);
		interfaze.addSuperInterface(type);
		interfaze.getMethods().removeIf((m) -> true);
		try {
			java.lang.reflect.Field e = interfaze.getClass().getDeclaredField("importedTypes");
			e.setAccessible(true);
			e.set(interfaze, new HashSet<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		interfaze.addImportedType(new FullyQualifiedJavaType(MAPPER));
		interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
		interfaze.addImportedType(defaultPrimaryType);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	private boolean ignoreColumn(String fieldName) {
		return this.ignoreColumns.stream().filter((ignoreColumn) -> {
			return ignoreColumn.equalsIgnoreCase(fieldName);
		}).count() <= 0L;
	}

	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return ignoreColumn(field.getName());
	}

	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		String fieldName = method.getName().replace("set", "");
		return ignoreColumn(fieldName);
	}

	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		String fieldName = method.getName().replace("get", "");
		return ignoreColumn(fieldName);
	}

	private void buildPrimaryKeyType(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		IntrospectedColumn column = null;
		if (columns != null && columns.size() > 0) {
			column = (IntrospectedColumn) columns.get(0);
		}

		if (column == null) {
			this.defaultPrimaryType = new FullyQualifiedJavaType(this.defaultPrimaryKey);
		} else {
			String javaType = "";
			String name = column.getJdbcTypeName().toLowerCase();
			switch (name) {
			case "bigint":
				javaType = "java.lang.Long";
				break;
			case "int":
				javaType = this.defaultPrimaryKey;
				break;
			case "nvarchar":
				javaType = "java.lang.String";
				break;
			case "varchar":
				javaType = "java.lang.String";
				break;
			default:
				javaType = this.defaultPrimaryKey;
			}
			this.defaultPrimaryType = new FullyQualifiedJavaType(javaType);
		}

	}
}
