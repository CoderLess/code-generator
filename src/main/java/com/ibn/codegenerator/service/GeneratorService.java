package com.ibn.codegenerator.service;

import com.ibn.codegenerator.anno.Generator;

import java.util.Map;

/**
 * @version 1.0
 * @description: 提供额外的属性信息
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/4/24 15:56
 */
@Generator
public interface GeneratorService {
    /**
     * @description: 设置模板的位置
     * @author：RenBin
     * @createTime：2020/4/25 22:08
     */
    String templatePath();
    /**
     * @description: 设置输出路径
     * @author：RenBin
     * @createTime：2020/4/25 22:09
     */
    String outPath();
    /**
     * @description: 在用户自定义信息中添加数据
     * @author：RenBin
     * @createTime：2020/4/25 22:06
     */
    Map<String, Object> addProperties();
}
