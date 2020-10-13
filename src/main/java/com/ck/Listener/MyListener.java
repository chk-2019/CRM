package com.ck.Listener;

import com.ck.domain.Activity;
import com.ck.domain.Dic_Value;
import com.ck.service.DataDicService;
import com.ck.service.DataDicServiceImpl;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.*;

public class MyListener extends ContextLoaderListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {

        super.contextInitialized(event);
        DataDicServiceImpl dataDicService = WebApplicationContextUtils
                .getWebApplicationContext(event.getServletContext())
                .getBean(DataDicServiceImpl.class);

        System.out.println("上下文监听器执行了");
        Map<String, List> valueTypeMap = dataDicService.getValueTypeMap();
        Set<String> keySet = valueTypeMap.keySet();
        TreeSet<String> keys = new TreeSet();
        keys.addAll(keySet);
        ServletContext servletContext = event.getServletContext();
        for (String key:keys){
            List<Dic_Value> list = valueTypeMap.get(key);
            Collections.sort(list);
            servletContext.setAttribute(key+"List",list);
        }
        ResourceBundle bundle = ResourceBundle.getBundle("com.ck.Listener.Stage2Possibility");
        Set<String >keyList = new TreeSet<>();
        Set<String> set = bundle.keySet();
        keyList.addAll(set);
        //System.out.println(keys);
        TreeMap<String, String> proMap = new TreeMap<String,String>();
        //System.out.println(keys);
        for (String s:keyList){
            String value = bundle.getString(s);
            proMap.put(s,value);
        }
        //System.out.println(proMap);
        //servletContext.setAttribute("stageList",keys);
        servletContext.setAttribute("proMap",proMap);

    }

    public MyListener() {
        super();
    }

    public MyListener(WebApplicationContext context) {
        super(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }
}
