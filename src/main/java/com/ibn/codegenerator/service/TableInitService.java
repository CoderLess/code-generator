package com.ibn.codegenerator.service;

import com.ibn.codegenerator.entity.TableDO;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/7/22 8:10
 */
public interface TableInitService {
    /**
     * @description: 初始化表的相关信息
     * @author：RenBin
     * @createTime：2020/7/22 8:11
     * @param tableDO
     */
    void initTable(TableDO tableDO);
}
