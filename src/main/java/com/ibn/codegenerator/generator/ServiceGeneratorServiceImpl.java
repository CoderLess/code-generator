package com.ibn.codegenerator.generator;

import com.ibn.codegenerator.entity.GeneratorDO;
import com.ibn.codegenerator.service.GeneratorService;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description: 生成service的类
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.generator
 * @author： RenBin
 * @createTime：2020/4/24 16:37
 */
@Service("serviceGeneratorService")
public class ServiceGeneratorServiceImpl implements GeneratorService {
    @Override
    public void generator(GeneratorDO generatorDO) {

    }
}
