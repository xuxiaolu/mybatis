package com.xuxl.mybatis.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ServiceGeneratorPlugin extends PluginAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceGeneratorPlugin.class);

    String target = null;
    String suffix = "Service";
    String packageName = null;
    String superInterface = "com.xuxl.mybatis.service.common.CommonService";
    String superClass = "com.xuxl.mybatis.service.common.AbstractCommonServiceImpl";
    String defaultPrimaryKeyClass = "java.lang.Long";

    private static final String spliter = ".";

    public boolean validate(List<String> warnings) {
        target = properties.getProperty("target");
        logger.info(target);
        if(StringUtils.isEmpty(target)){
            logger.error("targer can not be null");
            return false;
        }
        
        String suffixProperties = properties.getProperty("suffix");
        if(StringUtils.isEmpty(suffixProperties)) {
        	logger.info("suffix is null,use the default value 'Service' to continue.");
        } else {
            suffix = suffixProperties;
        }

        packageName = properties.getProperty("packageName");
        if(StringUtils.isEmpty(packageName)) {
        	logger.info("packageName is null,use the default value 'service' to automatic build.");
        }
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
    	
    	FullyQualifiedJavaType primaryKey = getPrimaryKeyType(introspectedTable);
    	
        FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        
        FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        
        FullyQualifiedJavaType criterionType = new FullyQualifiedJavaType(introspectedTable.getExampleType());
        
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        String modelName = modelType.getShortName();
        String logicName = packageName + spliter + modelName + suffix;
        Interface interfaze = new Interface(logicName);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        
        FullyQualifiedJavaType superInterfaceType = new FullyQualifiedJavaType(superInterface);
        superInterfaceType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        superInterfaceType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        superInterfaceType.addTypeArgument(primaryKey);
        interfaze.addSuperInterface(superInterfaceType);
        
        interfaze.addImportedType(new FullyQualifiedJavaType(superInterface));
        interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        interfaze.addImportedType(primaryKey);
        
        GeneratedJavaFile logicFile = new GeneratedJavaFile(interfaze, target,javaFormatter);
        
        //Generate logic Implementation
        String logicImplName = packageName + spliter + modelName + suffix + "Impl";
        TopLevelClass topLevelClass = new TopLevelClass(new FullyQualifiedJavaType(logicImplName));
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addSuperInterface(interfaze.getType());
        FullyQualifiedJavaType superClassType = new FullyQualifiedJavaType(superClass);
        superClassType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType()));
        superClassType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        superClassType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        superClassType.addTypeArgument(primaryKey);
        topLevelClass.setSuperClass(superClassType);
        topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType()));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Component"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(superClass));

        topLevelClass.addAnnotation("@Component");
        String fieldName = mapperType.getShortName().replaceFirst(mapperType.getShortName().substring(0, 1),mapperType.getShortName().substring(0, 1).toLowerCase()) ;
        Field field = new Field(fieldName,mapperType);
        field.addAnnotation("\n\t@Autowired");
        field.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.addField(field);
        Method method = new Method("getMapper");
        method.setReturnType(mapperType);
        method.addBodyLine(String.format("return %s;", fieldName));
        method.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addMethod(method);
        
        Method getCriteriaMethod = new Method("getCriteria");
        getCriteriaMethod.setReturnType(criterionType);
        getCriteriaMethod.addBodyLine(String.format("return new %s();", criterionType.getShortName()));
        getCriteriaMethod.setVisibility(JavaVisibility.PROTECTED);
        topLevelClass.addMethod(getCriteriaMethod);

        GeneratedJavaFile logicImplFile = new GeneratedJavaFile(topLevelClass,target,javaFormatter);

        List<GeneratedJavaFile> generatedJavaFileList = new ArrayList<>();
        generatedJavaFileList.add(logicFile);
        generatedJavaFileList.add(logicImplFile);
		return generatedJavaFileList;
    }
    
    private FullyQualifiedJavaType getPrimaryKeyType(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
		IntrospectedColumn column = null;
		if (columns != null && columns.size() > 0) {
			column = (IntrospectedColumn) columns.get(0);
		}
		if (column == null) {
			return new FullyQualifiedJavaType(defaultPrimaryKeyClass);
		} else {
			String name = column.getJdbcTypeName().toLowerCase();
			switch (name) {
			case "bigint":
				return new FullyQualifiedJavaType("java.lang.Long");
			case "int":
				return new FullyQualifiedJavaType("java.lang.Integer");
			case "nvarchar":
				return new FullyQualifiedJavaType("java.lang.String");
			case "varchar":
				return new FullyQualifiedJavaType("java.lang.String");
			default:
				return new FullyQualifiedJavaType(defaultPrimaryKeyClass);
			}
		}
    }
}
