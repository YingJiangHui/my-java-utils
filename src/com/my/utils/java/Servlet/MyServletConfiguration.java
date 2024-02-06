package com.my.utils.java.Servlet;

public class MyServletConfiguration {
    String name;
    String className;

    String path;
    //    初始化顺序 为负数时只有在请求时才对Servlet实例化。另外的数字越小初始化时的优先级越高
    int loadOnStartup;
    public MyServletConfiguration(String name,String className, String path) {
        this.name = name;
        this.className = className;
        this.path = path;
    }
    public MyServletConfiguration(String name, String className, String path, int loadOnStartup) {
        this(name,className,path);
        this.loadOnStartup = loadOnStartup;
    }
}
