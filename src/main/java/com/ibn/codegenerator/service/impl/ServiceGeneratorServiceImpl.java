package com.ibn.codegenerator.service.impl;

import com.google.common.collect.Maps;
import com.ibn.codegenerator.entity.SysConfigDO;
import com.ibn.codegenerator.entity.TableDO;
import com.ibn.codegenerator.entity.UserConfigDO;
import com.ibn.codegenerator.entity.VelocityConfigDO;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.GeneratorService;
import com.ibn.codegenerator.service.PlaceholderDealService;
import com.ibn.codegenerator.service.TemplateService;
import com.ibn.codegenerator.service.impl.TableServiceImpl;
import com.ibn.codegenerator.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @description: 生成service的类
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.generator
 * @author： RenBin
 * @createTime：2020/4/24 16:37
 */
@Service("serviceGenerator")
public class ServiceGeneratorServiceImpl implements GeneratorService, PlaceholderDealService {
    @Autowired
    private UserConfigDO userConfigDO;
    @Autowired
    private SysConfigDO sysConfigDO;
    @Autowired
    private VelocityConfigDO velocityConfigDO;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private TableServiceImpl tableService;
    // 记录占位符
    private Map<String,Object> placeholderMap;
    // 占位符替换以后的值
    private Map<String,Object> placeholderAfterReplaceMap;
    private static final Pattern placeholderPatter=Pattern.compile("\\#\\{(.{1,})\\}");


    @Override
    public void generate() throws SQLException, IbnException {
        // 获取表表信息
        List<TableDO> tableDOList = tableService.prepareTable();
        // 对表里的信息做一下初始化操作
        tableService.initTable();
        for (TableDO tableDO : tableDOList) {
            // 处理占位符
            Map<String, Object> placeholderDealMap = this.dealPlaceholder(tableDO);
            if (null != placeholderDealMap) {
                userConfigDO.getConfigMap().putAll(placeholderDealMap);
            }
            // 创建文件
            createFile( tableDO );
        }
    }

    private void createFile(TableDO tableDO ) {
        // 获取所有需要生成的模板
        Set<Map.Entry<String, String>> templateEntry = velocityConfigDO.getTemplate().entrySet();
        templateEntry.stream().forEach(entry -> {
            String templatePath = String.format("%s/%s",velocityConfigDO.getBasepath(),entry.getValue());
            String suffix = entry.getValue().replace(".vm", "");

            String outputPath = String.format("%s/%s%s", sysConfigDO.getBasepackage().replace(".", "/"),
                    tableDO.getEntityName(),suffix);
            try {
                userConfigDO.getConfigMap().put("table", tableDO);
                templateService.writer(userConfigDO.getConfigMap(),templatePath,outputPath);
            } catch (IbnException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Map<String, Object> dealPlaceholder(TableDO tableDO) {
        if (null == this.placeholderMap) {
            this.placeholderMap = Maps.newHashMap();
            this.getPlaceholder(tableDO);
        }
        Map<String, Object> resultMap = Maps.newHashMap();
        // 处理占位符
        if (this.placeholderMap.size() < 1) {
            return resultMap;
        }
        Set<Map.Entry<String, Object>> entries = placeholderMap.entrySet();
        placeholderAfterReplaceMap.entrySet().stream().forEach(
                entry -> {
                    String value= (String) entry.getValue();
                    entries.stream().forEach(
                            placeholderEntry -> {
                                String key= placeholderEntry.getKey();
                                String placeholder = String.format("#{%s}",key);
                                if (value.contains(placeholder)) {
                                    String entityName = value.replace(placeholder,
                                            (CharSequence) placeholderMap.get(key));
                                    resultMap.put(entry.getKey(),entityName);
                                }
                            }
                    );
                }
        );
        return resultMap;
    }
    /**
     * @description: 获取所有的占位符
     * @author：RenBin
     * @createTime：2020/7/22 10:28
     */
    private void getPlaceholder(TableDO tableDO) {
        if (null == placeholderAfterReplaceMap) {
            placeholderAfterReplaceMap = Maps.newHashMap();
        }
        Set<Map.Entry<String, Object>> entries = userConfigDO.getConfigMap().entrySet();
        entries.stream().forEach(entry -> {
            Matcher matcher = placeholderPatter.matcher((CharSequence) entry.getValue());
            while (matcher.find()) {
                String group = matcher.group(1);
                if ("entityName".equals(group)) {
                    placeholderMap.put(group, tableDO.getEntityName());
                }
                if (null == placeholderAfterReplaceMap.get(entry.getKey())) {
                    placeholderAfterReplaceMap.put(entry.getKey(), entry.getValue());
                }
            }
        });

    }
}
