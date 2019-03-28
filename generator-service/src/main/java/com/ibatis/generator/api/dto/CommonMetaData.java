package com.ibatis.generator.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午6:30
 * To change this template use File | Settings | File Templates.
 */
public class CommonMetaData implements Serializable {

    private String dataTitle;

    private List<MetaDataItem> dataPairs = new ArrayList<MetaDataItem>();

    public void addDataPair(MetaDataItem dataPair)
    {
        dataPairs.add(dataPair);
    }
    public void addDataPair(String name,String type)
    {
        this.addDataPair(new MetaDataItem(name,type));
    }


    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public List<MetaDataItem> getDataPairs() {
        return dataPairs;
    }

    public void setDataPairs(List<MetaDataItem> dataPairs) {
        this.dataPairs = dataPairs;
    }
}
