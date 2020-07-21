package com.ibn.codegenerator.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description: 系统使用必要的参数
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.entity
 * @author： RenBin
 * @createTime：2020/7/21 15:46
 */
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "sysconfig")
public class SysConfigDO {
    /**
     * @description: 基础包
     * @author：RenBin
     * @createTime：2020/7/21 15:47
     */
    private String basepackage;
    /**
     * @description: 哪些表需要生成代码
     * @author：RenBin
     * @createTime：2020/7/21 17:17
     */
    private List<String> generatetable;
}
