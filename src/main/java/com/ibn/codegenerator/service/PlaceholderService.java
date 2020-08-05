package com.ibn.codegenerator.service;

import com.ibn.codegenerator.entity.TableDO;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/7/22 9:53
 */
public interface PlaceholderService {
    /**
     * @description: 从环境变量中获取数据填充占位符
     * @author：RenBin
     * @createTime：2020/8/5 8:18
     */
    void fillPlaceholderFromEnvironment(Object object) throws NoSuchMethodException;
}
