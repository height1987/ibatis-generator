package com.ibatis.generator.web.factory;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ibatis.generator.api.util.JsonUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午6:38
 * To change this template use File | Settings | File Templates.
 */

public class MappingRuleFactory {


    private static Map<String, String> rule;


    public void init(String ruleJson) {
        rule = new HashMap<String, String>();

        try {
            final Map<String, Object> stringObjectMap = JsonUtils.fromJson(ruleJson,Map.class);
            for (String key : stringObjectMap.keySet()) {
                rule.put(key, stringObjectMap.get(key).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String propertyTypeMapping(String key) {
        final String key1 = key.toUpperCase();
        if (rule.containsKey(key1))
            return rule.get(key1);
        return "UN_MAPPING";
    }

    public String propertyNameMapping(String name) {
        if (StringUtils.isEmpty(name))
            return name;
        String lowName = name.toLowerCase();
        List<String> res = Lists.newArrayList();
        Splitter.on('_').splitToList(lowName).forEach(e->{res.add(e.substring(0, 1).toUpperCase() + e.substring(1, e.length()));});
        String newName = Joiner.on("").join(res);
        return newName.substring(0, 1).toLowerCase() + newName.substring(1, newName.length());
    }

    public String titleMapping(String name) {
        if (StringUtils.isEmpty(name))
            return name;
        final String[] split = name.split("_");
        return split[split.length - 1];
    }




}
