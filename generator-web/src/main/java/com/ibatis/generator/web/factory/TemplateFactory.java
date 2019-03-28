package com.ibatis.generator.web.factory;


import com.ibatis.generator.api.dto.ClassMetaData;
import com.ibatis.generator.api.dto.TableMetaData;
import com.ibatis.generator.web.constant.TemplateConstants;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.log.NullLogChute;
import org.springframework.stereotype.Service;
import org.apache.velocity.runtime.RuntimeConstants;


import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhenyu.yue
 * Date: 14-10-20
 * Time: 下午6:43
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TemplateFactory {

    private VelocityEngine velocityEngine;

    @PostConstruct
    public void init(){
        this.velocityEngine=new VelocityEngine();
        velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new NullLogChute());
        velocityEngine.init();
    }



    public String doTemplate(String path,Map<String,Object> properties)
    {
        Template t= velocityEngine.getTemplate(path,"utf-8");
        VelocityContext context = new VelocityContext(properties);
        StringWriter writer = new StringWriter();
        t.merge(context,writer);
        return writer.toString();
    }

    public String doEntityTemplate(ClassMetaData classMetaData)
    {
        Map<String,Object> properties  = new HashMap<String, Object>();
        properties.put("title",classMetaData.getDataTitle());
        properties.put("properties",classMetaData.getDataPairs());
        String path = TemplateConstants.DEFAULT_ENTITY_PATH;
        return  doTemplate(path,properties);
    }


    public String doSqlTemplate(ClassMetaData classMetaData,TableMetaData tableMetaData)
    {
        Map<String,Object> properties  = new HashMap<String, Object>();
        properties.put("classTitle",classMetaData.getDataTitle());
        properties.put("lowCaseClassTitle", getLowCaseClassTitle(classMetaData));

        properties.put("classCols",classMetaData.getDataPairs());

        properties.put("tableTitle",tableMetaData.getDataTitle());
        properties.put("tableCols",tableMetaData.getDataPairs());
        String path = TemplateConstants.DEFAULT_SQL_PATH;
        return  doTemplate(path, properties);
    }

    private String getLowCaseClassTitle(ClassMetaData classMetaData) {
        return classMetaData.getDataTitle().substring(0,1).toLowerCase()+classMetaData.getDataTitle().substring(1,classMetaData.getDataTitle().length());
    }


}
