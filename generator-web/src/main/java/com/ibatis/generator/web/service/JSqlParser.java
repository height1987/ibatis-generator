package com.ibatis.generator.web.service;

import com.ibatis.generator.api.dto.MetaDataItem;
import com.ibatis.generator.api.dto.TableMetaData;
import com.ibatis.generator.api.service.SqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-11-24
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
@Service
class JSqlParser implements SqlParser {
    @Override
    public TableMetaData parserSql(String sql) {

        String prepareSql = prepare(sql);


        Statement parse = null;
        try {
            parse = CCJSqlParserUtil.parse(prepareSql);
            System.out.println(parse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return translate(parse);
    }

    private String prepare(String sql) {

        return sql.replace("`", "").replace("ON UPDATE CURRENT_TIMESTAMP","");
    }

    private TableMetaData translate(Statement parse) {

        TableMetaData tableMetaData = new TableMetaData();

        final CreateTable createTable = (CreateTable) parse;

        tableMetaData.setDataTitle(createTable.getTable().getName());

        tableMetaData.setDataPairs(translate(createTable.getColumnDefinitions()));

        return tableMetaData;
    }

    private List<MetaDataItem> translate(List<ColumnDefinition> columnDefinitions) {
        List<MetaDataItem> dataItems = new ArrayList<MetaDataItem>();

        if(CollectionUtils.isEmpty(columnDefinitions))
            return dataItems;

        for(ColumnDefinition definition : columnDefinitions)
        {
            dataItems.add(translate(definition));
        }


        return dataItems;
    }

    private MetaDataItem translate(ColumnDefinition definition) {
        MetaDataItem item = new MetaDataItem();
        item.setDataName(definition.getColumnName());
        item.setDataType(definition.getColDataType().getDataType());
        return item;
    }
}
