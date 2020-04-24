package com.ibn.codegenerator.config;

import com.ibn.codegenerator.anno.Generator;
import com.google.common.collect.Lists;
import com.ibn.codegenerator.service.GeneratorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.config
 * @author： RenBin
 * @createTime：2020/4/24 16:33
 */
@Configuration
public class GeneratorConfig {
    @Bean
    List<GeneratorService> listGeneratorService(ApplicationContext applicationContext) {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Generator.class);
        if (beansWithAnnotation.size() <= 0) {
            return Lists.newArrayList();
        }
        List<GeneratorService> generatorServiceList = Lists.newArrayList();
        Set<Map.Entry<String, Object>> entries = beansWithAnnotation.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            generatorServiceList.add((GeneratorService) entry.getValue());
        }
        return generatorServiceList;
    }
}
