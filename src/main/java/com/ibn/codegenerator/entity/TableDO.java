package com.ibn.codegenerator.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @description: 表相关信息
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/7/22 8:12
 */
@Data
public class TableDO {
    /**
     * @description: 实例名称 UserBase
     * @author：RenBin
     * @createTime：2020/7/22 9:34
     */
    private String entityName;
    /**
     * @description: 实例名称 userBase
     * @author：RenBin
     * @createTime：2020/7/22 9:34
     */
    private String entityNameLow;
    /**
     * @description: 数据库名字
     * @author：RenBin
     * @createTime：2020/7/22 12:35
     */
    private String databaseName;
    /**
     * @description: 表名
     * @author：RenBin
     * @createTime：2020/7/22 12:35
     */
    private String tableName;
    /**
     * @description: 表信息数据
     * @author：RenBin
     * @createTime：2020/7/22 9:34
     */
    private List<FieldDO> fieldDOList;
    /**
     * @description: 表注释
     * @author：RenBin
     * @createTime：2020/7/22 15:23
     */
    private String remark;
}
