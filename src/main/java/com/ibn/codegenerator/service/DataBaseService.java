package com.ibn.codegenerator.service;

import com.ibn.codegenerator.exception.IbnException;
import org.mybatis.generator.internal.db.DatabaseIntrospector;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/4/24 11:49
 */
public interface DataBaseService {
    /**
     * @description: 获取数据库解析器
     * @author：RenBin
     * @createTime：2020/4/24 12:01
     */
    DatabaseIntrospector databaseIntrospector() throws IbnException;
}
