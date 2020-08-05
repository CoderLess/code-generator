package com.ibn.codegenerator.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/7/21 16:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "velocityconfig")
public class VelocityConfigDO implements Serializable {
    /**
     * @description: velocity模板路径
     * @author：RenBin
     * @createTime：2020/7/21 16:42
     */
    private String basepath;
    /**
     * @description: 需要生成的模板
     * @author：RenBin
     * @createTime：2020/7/21 16:43
     */
    private Map<String,VelocityTemplateDO> template;
}
