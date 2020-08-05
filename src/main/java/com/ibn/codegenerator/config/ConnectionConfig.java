package com.ibn.codegenerator.config;

import com.ibn.codegenerator.entity.ConnectionDO;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @description: 数据库连接的相关配置
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.config
 * @author： RenBin
 * @createTime：2020/4/24 10:14
 */
@Configuration
public class ConnectionConfig {
    /**
     * @description: 根据配置生成数据库的连接方式
     * @author：RenBin
     * @createTime：2020/4/24 10:15
     */
    @Bean
    public JDBCConnectionConfiguration jdbcConnectionConfiguration(ConnectionDO connectionDO) {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        // 对象中值的拷贝（不建议用apache的）
        BeanUtils.copyProperties(connectionDO, jdbcConnectionConfiguration);
        return jdbcConnectionConfiguration;
    }
}

