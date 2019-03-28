package com.ibatis.generator.api.service;


import com.ibatis.generator.api.dto.ClassMetaData;
import com.ibatis.generator.api.dto.TableMetaData;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午6:44
 * To change this template use File | Settings | File Templates.
 */
public interface SqlGenerator {

    String generate(TableMetaData tableData, ClassMetaData classData);
}
