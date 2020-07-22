package com.ibn.codegenerator.service.impl;

import com.google.common.collect.Lists;
import com.ibn.codegenerator.entity.FieldDO;
import com.ibn.codegenerator.entity.SysConfigDO;
import com.ibn.codegenerator.entity.TableDO;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.ConnectionService;
import com.ibn.codegenerator.service.InitTableService;
import com.ibn.codegenerator.service.TablePrepareService;
import com.ibn.codegenerator.util.StringUtil;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.db.DatabaseIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/7/22 8:00
 */
@Service("tableService")
public class TableServiceImpl implements TablePrepareService, InitTableService {
    private static final Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);
    @Autowired
    private SysConfigDO sysConfigDO;
    @Autowired
    private ConnectionService connectionService;

    private List<TableDO> tableDOList;
    @Override
    public List<TableDO> prepareTable() throws SQLException, IbnException {
        this.tableDOList = Lists.newArrayList();
        // 获取所有需要配置的表
        List<String> generatetable = sysConfigDO.getGeneratetable();
        if (CollectionUtils.isEmpty(generatetable)) {
            return Lists.newArrayList();
        }
        List<String> warnings = Lists.newArrayList();
        Context context = new Context(null);
        JavaTypeResolver javaTypeResolver = ObjectFactory
                .createJavaTypeResolver(context, warnings);
        // 获取表信息的核心
        DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(
                context, connectionService.createConnection().getMetaData(), javaTypeResolver, warnings);
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        // 存放表信息
        for (String tableName : generatetable) {
            String[] split = tableName.split("\\.");
            if (split.length != 2) {
                logger.error("用户指定表信息（{}）格式错误请使用 databaseName.tableName格式指定表信息",tableName);
                continue;
            }
            TableDO tableDO = new TableDO();
            // 获取库名和表名
            tableDO.setDatabaseName(split[0]);
            tableDO.setTableName(split[1]);

            // 设置entityName
            String upcamelCaseTableName = StringUtil.underscoreToUpcamelCase(tableDO.getTableName());
            tableDO.setEntityName(StringUtil.upperCaseFirstLatter(upcamelCaseTableName));
            tableDO.setEntityNameLow(StringUtil.lowCaseFirstLatter(upcamelCaseTableName));

            tableConfiguration.setSchema(split[0]);
            tableConfiguration.setTableName(split[1]);
            List<IntrospectedTable> tables = databaseIntrospector
                    .introspectTables(tableConfiguration);
            IntrospectedTable introspectedTable = tables.get(0);
            // 获取表注释
            tableDO.setRemark(introspectedTable.getRemarks());
            // 获取所有字段
            List<IntrospectedColumn> allColumnList = introspectedTable.getAllColumns();
            List<FieldDO> filedDOList = Lists.newArrayList();
            for (IntrospectedColumn introspectedColumn : allColumnList) {
                String actualColumnName = introspectedColumn.getActualColumnName();
                String upcamelCaseName = StringUtil.underscoreToUpcamelCase(actualColumnName);
                FieldDO fieldDO = new FieldDO();
                fieldDO.setFieldName(actualColumnName);
                fieldDO.setFileType(introspectedColumn.getActualTypeName());

                if ("BIGINT UNSIGNED".equals(introspectedColumn.getActualTypeName())||"BIGINT".equals(introspectedColumn.getActualTypeName())) {
                    fieldDO.setAttributeType("Long");
                } else if ("INT".equals(introspectedColumn.getActualTypeName())) {
                    fieldDO.setAttributeType("Integer");
                } else if ("DECIMAL".equals(introspectedColumn.getActualTypeName())) {
                    fieldDO.setAttributeType("BigDecimal");
                } else {
                    fieldDO.setAttributeType("String");
                }
//                System.out.println(String.format("%s---->%s",actualColumnName,introspectedColumn.getActualTypeName()));
                fieldDO.setParameterName(StringUtil.lowCaseFirstLatter(upcamelCaseName));
                fieldDO.setAttributeName(StringUtil.upperCaseFirstLatter(upcamelCaseName));
                fieldDO.setRemark(introspectedColumn.getRemarks());
                filedDOList.add(fieldDO);
            }
            tableDO.setFieldDOList(filedDOList);
            tableDOList.add(tableDO);
        }
        // 放到系统变量中
        sysConfigDO.setTableDOList(tableDOList);
        return tableDOList;
    }

    @Override
    public void initTable() {
    }
}
