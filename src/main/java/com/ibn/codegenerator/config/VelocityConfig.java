package com.ibn.codegenerator.config;

import com.ibn.codegenerator.constant.CharacterVal;
import com.ibn.codegenerator.constant.VelocityVal;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.config
 * @author： RenBin
 * @createTime：2020/4/24 15:17
 */
@Configuration
public class VelocityConfig {
    /**
     * @description: 生成一个VelocityEngine
     * @author：RenBin
     * @createTime：2020/4/24 15:21
     */
    @Bean
    public VelocityEngine velocityEngine() {
        Properties properties = new Properties();
        properties.setProperty(VelocityVal.VM_LOAD_PATH_KEY, VelocityVal.VM_LOAD_PATH_VALUE);
        properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
        properties.setProperty(Velocity.ENCODING_DEFAULT, CharacterVal.UTF8);
        properties.setProperty(Velocity.INPUT_ENCODING, CharacterVal.UTF8);
        properties.setProperty("file.resource.loader.unicode", "true");
        return new VelocityEngine(properties);
    }
}
