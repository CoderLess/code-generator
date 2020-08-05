package com.ibn.codegenerator.service.impl;

import com.google.common.collect.Lists;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.ConnectionService;
import com.ibn.codegenerator.service.DataBaseService;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.db.DatabaseIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @description: 获取表信息
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service.impl
 * @author： RenBin
 * @createTime：2020/4/24 12:37
 */
@Service("dataBaseService")
public class DataBaseServiceImpl implements DataBaseService {
    @Autowired
    private ConnectionService connectionService;
    @Override
    public DatabaseIntrospector databaseIntrospector() throws IbnException {
        List<String> warnings = Lists.newArrayList();
        Context context = new Context(null);
        JavaTypeResolver javaTypeResolver = ObjectFactory
                .createJavaTypeResolver(context, warnings);
        // 用来获取表信息的核心数据
        try {
             return new DatabaseIntrospector(
                    context, connectionService.createConnection().getMetaData(), javaTypeResolver, warnings);
        } catch (Exception e) {
            throw new IbnException("创建DatabaseIntrospector失败");
        }
    }
}
