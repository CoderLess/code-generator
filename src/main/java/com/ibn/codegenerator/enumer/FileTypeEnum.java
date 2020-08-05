package com.ibn.codegenerator.enumer;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.enumer
 * @author： RenBin
 * @createTime：2020/8/5 11:42
 */
public enum FileTypeEnum {
    CLASS("类文件","class"),
    TEST("测试文件", "test"),
    MAPPER("映射文件","mapper");

    private String desc;
    private String value;

    FileTypeEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }
}
