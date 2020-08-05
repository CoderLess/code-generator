package com.ibn.codegenerator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/8/4 8:43
 */
@Data
public class VelocityTemplateDO implements Serializable {
    /**
     * @description: 模板名称
     * @author：RenBin
     * @createTime：2020/8/4 8:44
     */
    private String name;
    /**
     * @description: 项目名称
     * @author：RenBin
     * @createTime：2020/8/4 11:17
     */
    private String module;
    /**
     * @description: 文件类型
     * @author：RenBin
     * @createTime：2020/8/5 11:44
     */
    private String type;
    /**
     * @description: 输出路径
     * @author：RenBin
     * @createTime：2020/8/4 8:44
     */
    private String outPutPackage;
}
