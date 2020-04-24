package com.ibn.codegenerator.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/4/24 10:23
 */
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "databaseinfo")
public class ConnectionDO {
    /**
     * @description: 驱动类
     * @author：RenBin
     * @createTime：2020/4/24 10:28
     */
    private String driverClass;
    /**
     * @description: 连接数据库的url
     * @author：RenBin
     * @createTime：2020/4/24 10:28
     */
    private String connectionURL;
    /**
     * @description: 用户名
     * @author：RenBin
     * @createTime：2020/4/24 10:29
     */
    private String userId;
    /**
     * @description: 密码
     * @author：RenBin
     * @createTime：2020/4/24 10:30
     */
    private String password;
}
