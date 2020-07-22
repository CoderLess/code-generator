package com.ibn.codegenerator.entity;

import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/7/22 12:00
 */
@Data
public class FieldDO {
    /**
     * @description: 字段名 : user_name
     * @author：RenBin
     * @createTime：2020/7/22 12:32
     */
    private String fieldName;
    /**
     * @description: 字段类型
     * @author：RenBin
     * @createTime：2020/7/22 12:32
     */
    private String fileType;
    /**
     * @description: 属性类型 ：
     * @author：RenBin
     * @createTime：2020/7/22 12:41
     */
    private String attributeType;
    /**
     * @description: 属性名 ： UserName
     * @author：RenBin
     * @createTime：2020/7/22 20:17
     */
    private String attributeName;
    /**
     * @description: 形参名 : userName
     * @author：RenBin
     * @createTime：2020/7/22 12:42
     */
    private String parameterName;
    /**
     * @description: 字段注释
     * @author：RenBin
     * @createTime：2020/7/22 15:23
     */
    private String remark;
}
