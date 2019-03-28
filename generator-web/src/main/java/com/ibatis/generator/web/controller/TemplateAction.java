package com.ibatis.generator.web.controller;


import com.ibatis.generator.api.dto.ClassMetaData;
import com.ibatis.generator.api.dto.TableMetaData;
import com.ibatis.generator.api.util.JsonUtils;
import com.ibatis.generator.web.factory.TemplateFactory;
import com.ibatis.generator.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-12-9
 * Time: 下午7:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/template")
public class TemplateAction {


    private String entity;
    private String sql;


    @Autowired
    private TemplateFactory templateFactory;





    @RequestMapping(value = "/generate",produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    protected String generate(String tableMetaData,String classMetaData) {

        final ClassMetaData cData = JsonUtils.fromJson(classMetaData, ClassMetaData.class);

        entity = templateFactory.doEntityTemplate(cData);

        final TableMetaData tData = JsonUtils.fromJson(tableMetaData, TableMetaData.class);

        sql = templateFactory.doSqlTemplate(cData,tData);

        return JsonUtils.toJson(build(entity,sql));
    }
    private Map<String , Object> build(String entity, String sql){
        Map<String,Object> res = new HashMap<>();
        res.put("entity",entity);
        res.put("sql",sql);
        return res;
    }

}
