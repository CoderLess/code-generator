package com.ibn.codegenerator.service;

import com.ibn.codegenerator.anno.Generator;
import com.ibn.codegenerator.entity.GeneratorDO;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service
 * @author： RenBin
 * @createTime：2020/4/24 15:56
 */
@Generator
public interface GeneratorService {
    /**
     * @description: 生成文件
     * @author：RenBin
     * @createTime：2020/4/24 15:57
     */
    void generator(GeneratorDO generatorDO);
}
