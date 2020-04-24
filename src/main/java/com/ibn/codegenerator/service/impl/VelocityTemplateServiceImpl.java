package com.ibn.codegenerator.service.impl;

import com.ibn.codegenerator.constant.CharacterVal;
import com.ibn.codegenerator.exception.IbnException;
import com.ibn.codegenerator.service.TemplateService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service.impl
 * @author： RenBin
 * @createTime：2020/4/24 15:15
 */
public class VelocityTemplateServiceImpl implements TemplateService {
    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws IbnException {
        if (StringUtils.isEmpty(templatePath)) {
            return;
        }
        Template template = velocityEngine.getTemplate(templatePath, CharacterVal.UTF8);
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             OutputStreamWriter ow = new OutputStreamWriter(fos, CharacterVal.UTF8);
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(objectMap), writer);
        } catch (IOException e) {
            throw new IbnException("写出文件异常", e);
        }
    }
}
