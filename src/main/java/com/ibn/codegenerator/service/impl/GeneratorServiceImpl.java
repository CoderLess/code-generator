package com.ibn.codegenerator.service.impl;

import com.ibn.codegenerator.entity.*;
import com.ibn.codegenerator.enumer.FileTypeEnum;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.GeneratorService;
import com.ibn.codegenerator.service.PlaceholderService;
import com.ibn.codegenerator.service.TableService;
import com.ibn.codegenerator.service.TemplateService;
import com.ibn.codegenerator.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @description: 生成service的类
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.generator
 * @author： RenBin
 * @createTime：2020/4/24 16:37
 */
@Service("serviceGenerator")
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private TableService tableService;
    @Autowired
    private PlaceholderService placeholderService;
    @Autowired
    private UserConfigDO userConfigDO;
    @Autowired
    private VelocityConfigDO velocityConfigDO;
    @Autowired
    private SysConfigDO sysConfigDO;

    @Override
    public void generate() throws SQLException, IbnException, NoSuchMethodException {
        UserConfigDO srcUserConfigDO = (UserConfigDO) BeanUtils.cloneObjBySerialization(userConfigDO);
        VelocityConfigDO srcVelocityConfigDO = (VelocityConfigDO) BeanUtils.cloneObjBySerialization(velocityConfigDO);
        // 获取表表信息
        List<TableDO> tableDOList = tableService.prepareTable();
        for (TableDO tableDO : tableDOList) {
            // 对表里的信息做一下初始化操作
            tableService.initTable(tableDO);
            // 深度拷贝获取原始的配置信息
            userConfigDO = (UserConfigDO) BeanUtils.cloneObjBySerialization(srcUserConfigDO);
            velocityConfigDO= (VelocityConfigDO) BeanUtils.cloneObjBySerialization(srcVelocityConfigDO);
            // 处理占位符
            placeholderService.fillPlaceholderFromEnvironment(userConfigDO);
            placeholderService.fillPlaceholderFromEnvironment(velocityConfigDO);
            // 将表里的信息添加到map中
            userConfigDO.getConfigMap().put("table", tableDO);
            // 创建文件
            createFile( tableDO );
        }
    }

    private void createFile(TableDO tableDO ) {
        // 获取所有需要生成的模板
        Set<Map.Entry<String, VelocityTemplateDO>> templateEntry = velocityConfigDO.getTemplate().entrySet();
        templateEntry.stream().forEach(entry -> {
            String templatePath = String.format("%s/%s",velocityConfigDO.getBasepath(),entry.getValue().getName());
            VelocityTemplateDO velocityTemplateDO = entry.getValue();
            String suffix = velocityTemplateDO.getName().replace(".vm", "");

            String outputPath = this.createOutPath(tableDO, suffix, velocityTemplateDO);
            try {
                templateService.writer(userConfigDO.getConfigMap(),templatePath,outputPath);
            } catch (IbnException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * @description: 构造输出路径
     * @author：RenBin
     * @createTime：2020/8/5 13:27
     */
    private String createOutPath(TableDO tableDO, String suffix, VelocityTemplateDO velocityTemplateDO) {
        String outputPath;
        if (FileTypeEnum.MAPPER.getValue().equals(velocityTemplateDO.getType())) {
            outputPath = String.format("%s/src/main/java/resources/%s/%s%s",
                    velocityTemplateDO.getModule(),
                    velocityTemplateDO.getOutPutPackage(),
                    tableDO.getEntityName(), suffix);
        } else  if (FileTypeEnum.TEST.getValue().equals(velocityTemplateDO.getType())) {
            outputPath = String.format("%s/src/main/test/java/%s/%s/%s%s",
                    velocityTemplateDO.getModule(),
                    sysConfigDO.getBasepackage().replace(".", "/"),
                    velocityTemplateDO.getOutPutPackage(),
                    tableDO.getEntityName(), suffix);
        }else {
            outputPath = String.format("%s/src/main/java/%s/%s/%s%s",
                    velocityTemplateDO.getModule(),
                    sysConfigDO.getBasepackage().replace(".", "/"),
                    velocityTemplateDO.getOutPutPackage(),
                    tableDO.getEntityName(), suffix);
        }

        return outputPath;
    }


}
