package com.ibn.codegenerator.generator;

import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.GeneratorService;
import com.ibn.codegenerator.service.TemplateService;
import com.ibn.codegenerator.service.impl.VelocityTemplateServiceImpl;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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


    @Override
    public String templatePath() {
        return "/templates/service.java.vm";
    }

    @Override
    public String outPath() {
        return "test.java";
    }

    @Override
    public Map<String, Object> addProperties() {
        return null;
    }
}
