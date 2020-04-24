package com.ibn.codegenerator.service;

import com.ibn.codegenerator.exception.IbnException;

import java.util.Map;

/**
 * @version 1.0
 * @description: 模板生成服务接口
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/4/24 15:12
 */
public interface TemplateService {
    /**
     * @description: 写出文件
     * @author：RenBin
     * @createTime：2020/4/24 15:14
     */
    void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws IbnException;
}
