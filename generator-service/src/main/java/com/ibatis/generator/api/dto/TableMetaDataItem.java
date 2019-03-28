package com.ibatis.generator.api.dto;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-11-20
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class TableMetaDataItem extends MetaDataItem {
    private boolean nullable;

    public TableMetaDataItem(String name,String type,boolean nullable)
    {
        this.setNullable(nullable);
        this.setDataName(name);
        this.setDataType(type);
    }


    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
