package com.ibatis.generator.web.controller;

import com.ibatis.generator.api.dto.ClassMetaData;
import com.ibatis.generator.api.dto.TableMetaData;
import com.ibatis.generator.api.service.PropertyMapping;
import com.ibatis.generator.api.service.SqlParser;
import com.ibatis.generator.api.util.JsonUtils;
import com.ibatis.generator.web.vo.JsonReturn;
import org.apache.commons.lang.StringUtils;
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
 * Date: 14-11-23
 * Time: 下午12:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/parseSql")
public class ParseSqlAction {


    @Autowired
    SqlParser sqlParser;

    @Autowired
    PropertyMapping propertyMapping;


    private String DEFAULT_MAPPING = "{\"INT\":\"Integer\",\"TINYINT\":\"Integer\",\"SMALLINT\":\"Integer\",\"BIGINT\":\"Integer\",\"MEDIUMINT\":\"Integer\",\"INTEGER\":\"Integer\",\"FLOAT\":\"Float\",\"DOUBLE\":\"Double\",\"DECIMAL\":\"BigDecimal\",\"VARCHAR\":\"String\",\"CHAR\":\"String\",\"TINYTEXT\":\"String\",\"TEXT\":\"String\",\"MEDIUMTEXT\":\"String\",\"LONGTEXT\":\"String\",\"DATE\":\"Date\",\"DATESTAMP\":\"Date\",\"TIMESTAMP\":\"Date\",\"DATETIME\":\"Date\"}";


    @RequestMapping(value="/parse",produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    protected String parse(String originSql, String nameMapping) throws Exception {

        TableMetaData tableMetaData = sqlParser.parserSql(originSql);

        ClassMetaData classMetaData = propertyMapping.tableToClass(tableMetaData, StringUtils.isBlank(nameMapping) ? DEFAULT_MAPPING : nameMapping);
        return JsonUtils.toJson(build(tableMetaData,classMetaData));
    }

    private Map<String, Object> build(TableMetaData table, ClassMetaData classMetaData) {
        Map<String, Object> res = new HashMap<>();
        res.put("tableMetaData", table);
        res.put("classMetaData", classMetaData);
        return res;
    }

}

