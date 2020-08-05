package com.ibn.codegenerator.service.impl;

import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.ConnectionService;
import org.mybatis.generator.api.ConnectionFactory;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @version 1.0
 * @description: 数据库连接
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service.impl
 * @author： RenBin
 * @createTime：2020/4/24 10:11
 */
@Service("connectionService")
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    private JDBCConnectionConfiguration jdbcConnectionConfiguration;
    @Override
    public Connection createConnection() throws SQLException, IbnException {
        ConnectionFactory connectionFactory;
        if (jdbcConnectionConfiguration == null) {
            throw new IbnException("数据库连接方式获取失败");
        }
        connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
        return connectionFactory.getConnection();
    }
}
