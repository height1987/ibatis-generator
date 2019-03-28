package com.ibatis.generator.web.service;


import com.ibatis.generator.api.dto.ClassMetaData;
import com.ibatis.generator.api.dto.MetaDataItem;
import com.ibatis.generator.api.dto.TableMetaData;
import com.ibatis.generator.api.service.PropertyMapping;
import com.ibatis.generator.web.factory.MappingRuleFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午7:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommonPropertyMapping implements PropertyMapping {
    @Override
    public ClassMetaData tableToClass(TableMetaData tableData, String rule) {


        MappingRuleFactory mappingRuleFactory = new MappingRuleFactory();

        mappingRuleFactory.init(rule);

        ClassMetaData classMetaData = new ClassMetaData();

        classMetaData.setDataTitle(mappingRuleFactory.titleMapping(tableData.getDataTitle()));

        classMetaData.setDataPairs(translate(mappingRuleFactory,tableData.getDataPairs()));


        return classMetaData;
    }

    private List<MetaDataItem> translate(MappingRuleFactory mappingRuleFactory, List<MetaDataItem> dataPairs) {
        List<MetaDataItem> result = new ArrayList<MetaDataItem>();
        for(MetaDataItem item : dataPairs)
        {
            result.add(translate(mappingRuleFactory,item));
        }
        return result;
    }

    private MetaDataItem translate(MappingRuleFactory mappingRuleFactory, MetaDataItem item) {
        MetaDataItem result = new MetaDataItem();

        result.setDataName(mappingRuleFactory.propertyNameMapping(item.getDataName()));
        result.setDataType(mappingRuleFactory.propertyTypeMapping(item.getDataType()));
        return result;
    }
}
