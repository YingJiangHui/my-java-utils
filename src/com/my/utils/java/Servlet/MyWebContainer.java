package com.my.utils.java.Servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class MyWebContainer {
    MyServletConfiguration[] myServletConfigurations;
    
    HashMap<String,MyServletConfiguration> pathToConfiguration;
    
    HashMap<String,MyServlet> pathToMyServletInstance;
    
    private MyServlet instantiationServlet(String className) {
        try {
            Class MyServletClazz = Class.forName(className);
            MyServlet myServlet = (MyServlet) MyServletClazz.getConstructor().newInstance();
            myServlet.init();
            this.pathToMyServletInstance.put(className, myServlet);
            return myServlet;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("实例化失败");
        }

    }
    
    public void MyWebContainer(MyServletConfiguration[] myServletConfigurations){
        this.myServletConfigurations = myServletConfigurations;
        this.pathToMyServletInstance = new HashMap<>();
        this.pathToConfiguration = new HashMap<>();

        Arrays.sort(this.myServletConfigurations, Comparator.comparingInt(a -> a.loadOnStartup));

        for(MyServletConfiguration myServletConfiguration : this.myServletConfigurations){
            this.pathToConfiguration.put(myServletConfiguration.path,myServletConfiguration);
            if(myServletConfiguration.loadOnStartup>=0){
                //启动时初始化
                this.instantiationServlet(myServletConfiguration.className);
            }
        }
    }
    public void doService(String path){
        MyServlet servletInstance = this.pathToMyServletInstance.get(path);
        if(servletInstance==null) {
            servletInstance = this.instantiationServlet(this.pathToConfiguration.get(path).className);
        }
        servletInstance.service();
    }
}
