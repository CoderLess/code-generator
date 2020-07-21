package com.ibn.codegenerator.generator;

import com.ibn.codegenerator.entity.SysConfigDO;
import com.ibn.codegenerator.entity.UserConfigDO;
import com.ibn.codegenerator.entity.VelocityConfigDO;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.GeneratorService;
import com.ibn.codegenerator.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ServiceGeneratorServiceImpl implements GeneratorService {
    @Autowired
    private UserConfigDO userConfigDO;
    @Autowired
    private SysConfigDO sysConfigDO;
    @Autowired
    private VelocityConfigDO velocityConfigDO;
    @Autowired
    private TemplateService templateService;


    @Override
    public void generate() {

        // 获取所有需要生成的模板
        Set<Map.Entry<String, String>> templateEntry = velocityConfigDO.getTemplate().entrySet();
        templateEntry.stream().forEach(entry -> {
            String subPackageName = entry.getKey();
            String templatePath = String.format("%s/%s",velocityConfigDO.getBasepath(),entry.getValue());
            String outputPath = String.format("%s/%s.java", sysConfigDO.getBasepackage().replace(".", "/"),
                    subPackageName);
            try {
                templateService.writer(userConfigDO.getConfigMap(),templatePath,outputPath);
            } catch (IbnException e) {
                e.printStackTrace();
            }
        });

    }
}
