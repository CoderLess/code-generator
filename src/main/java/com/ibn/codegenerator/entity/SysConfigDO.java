package com.ibn.codegenerator.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

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
public class SysConfigDO implements Serializable {
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
    /**
     * @description: 表的相关信息
     * @author：RenBin
     * @createTime：2020/7/22 8:14
     */
    private List<TableDO> tableDOList;
}
