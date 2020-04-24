package com.ibn.codegenerator.service;

import com.ibn.codegenerator.exception.IbnException;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.util
 * @author： RenBin
 * @createTime：2020/4/24 10:09
 */
public interface ConnectionService {
    /**
     * @description: 获取连接信息
     * @author：RenBin
     * @createTime：2020/4/24 10:10
     */
    Connection createConnection() throws SQLException, IbnException;
}
