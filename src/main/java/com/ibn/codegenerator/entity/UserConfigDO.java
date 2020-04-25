package com.ibn.codegenerator.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/4/25 18:44
 */
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "userconfig")
public class UserConfigDO {
    /**
     * @description: 用户自定义配置
     * @author：RenBin
     * @createTime：2020/4/25 18:45
     */
    private Map<String,Object> configMap;
}
