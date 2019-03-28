package com.ibatis.generator.api.service;


import com.ibatis.generator.api.dto.TableMetaData;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
public interface SqlParser {

    TableMetaData parserSql(String sql);
}
