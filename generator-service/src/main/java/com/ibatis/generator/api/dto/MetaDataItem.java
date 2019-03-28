package com.ibatis.generator.api.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午6:30
 * To change this template use File | Settings | File Templates.
 */
public class MetaDataItem implements Serializable {


    private  String dataName;

    private String dataType;



    public MetaDataItem()
    {
    }

    public MetaDataItem(String dataName, String dataType)
    {
        this.dataName = dataName;
        this.dataType =dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

}
