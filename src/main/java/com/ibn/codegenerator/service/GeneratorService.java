package com.ibn.codegenerator.service;

import com.ibn.codegenerator.exception.IbnException;

import java.sql.SQLException;

/**
 * @version 1.0
 * @description: 提供额外的属性信息
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/4/24 15:56
 */
public interface GeneratorService {
    /**
     * @description: 生成代码
     * @author：RenBin
     * @createTime：2020/7/21 15:57
     */
    void generate() throws SQLException, IbnException, NoSuchMethodException;
}

