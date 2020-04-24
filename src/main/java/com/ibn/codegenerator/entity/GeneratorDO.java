package com.ibn.codegenerator.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/4/24 15:59
 */
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "ibn.generator")
public class GeneratorDO {
    @NestedConfigurationProperty
    private CommonConfigDO commonConfigDO;
}
