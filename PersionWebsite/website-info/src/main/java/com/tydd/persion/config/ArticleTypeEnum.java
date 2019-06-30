package com.tydd.persion.config;

/**
 * @ClassName ArticleTypeEnum
 * @Description 文章类型枚举
 * @Author kun
 * @Date 2019/5/12
 * @Version 1.0
 */
public enum ArticleTypeEnum {

    PROGRAM(1, "编程技术"),
    PHOTOGRAPHY(2, "摄影交流"),
    NEWS(3, "时政要闻");

    ArticleTypeEnum(int typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    private int typeCode;

    private String typeName;

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}
