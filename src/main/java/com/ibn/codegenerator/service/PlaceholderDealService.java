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
public interface PlaceholderDealService {
    Map<String,Object> dealPlaceholder(TableDO tableDO);
}
