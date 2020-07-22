package com.ibn.codegenerator.service;

import com.ibn.codegenerator.entity.TableDO;
import com.ibn.codegenerator.exception.IbnException;
import org.mybatis.generator.api.IntrospectedTable;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/7/22 8:00
 */
public interface TablePrepareService {
    /**
     * @description: 获取表的基本信息
     * @author：RenBin
     * @createTime：2020/7/22 8:09
     * @return
     */
    List<TableDO> prepareTable() throws SQLException, IbnException;
}
