package com.ibn.codegenerator.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new VelocityEngine();
    }
}
