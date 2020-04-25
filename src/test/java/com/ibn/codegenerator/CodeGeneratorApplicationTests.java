package com.ibn.codegenerator;

import com.ibn.codegenerator.config.VelocityConfig;
import com.ibn.codegenerator.entity.ConnectionDO;
import com.ibn.codegenerator.entity.UserConfigDO;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.ConnectionService;
import com.ibn.codegenerator.service.GeneratorService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.db.DatabaseIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CodeGeneratorApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private ConnectionDO connectionDO;
    @Autowired
    private UserConfigDO userConfigDO;
    @Autowired
    private List<GeneratorService> listGeneratorService;
    /**
     * @description: 获取application中的所有beanname
     * @author：RenBin
     * @createTime：2020/4/24 14:05
     */
    @Test
    void contextLoads() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
            if (beanDefinitionName.equals("velocityConfig")) {
                VelocityConfig velocityConfig = (VelocityConfig) applicationContext.getBean(beanDefinitionName);
                System.out.println(velocityConfig.velocityEngine().getProperty("resource-loader-path"));
            }
        }
    }
    /**
     * @description: 利用mybatis-generator生成代码
     * @author：RenBin
     * @createTime：2020/4/24 14:06
     */
    @Test
    void xmlConfiguration() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    void codeConfiguration() throws Exception {
        List<String> warnings = new ArrayList<>();
        Configuration config = new Configuration();

        //   ... fill out the config object as appropriate...

        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    /**
     * @description: 获取表的信息
     * @author：RenBin
     * @createTime：2020/4/24 14:04
     */
    @Test
    void generate() throws SQLException, IbnException {
        List<String> warnings = Lists.newArrayList();
        Context context = new Context(null);
        JavaTypeResolver javaTypeResolver = ObjectFactory
                .createJavaTypeResolver(context, warnings);
        // 获取表信息的核心
        DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(
                context, connectionService.createConnection().getMetaData(), javaTypeResolver, warnings);
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setSchema("springbootplus");
        tableConfiguration.setTableName("foo_bar");
        List<IntrospectedTable> tables = databaseIntrospector
                .introspectTables(tableConfiguration);
        System.out.println(tables.size());
    }
    /**
     * @description: 从对象中获取属性名及属性值
     * @author：RenBin
     * @createTime：2020/4/24 14:03
     */
    @Test
    void testPropertyDescriptor() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(connectionDO.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method getter = property.getReadMethod();
            Object value = getter.invoke(connectionDO);
            System.out.println(String.format("%s->%s",property.getName(),value));
        }
    }
    /**
     * @description: 将yml中的属性映射到DO中
     * @author：RenBin
     * @createTime：2020/4/24 16:21
     */
    @Test
    void readPropertiesFormYml() {
        System.out.println(userConfigDO.getConfigMap().get("author"));
    }
    /**
     * @description: 生成代码
     * @author：RenBin
     * @createTime：2020/4/25 20:12
     */
    @Test
    void createCode() throws IbnException {
        for (GeneratorService generatorService : listGeneratorService) {
        }
    }
}
