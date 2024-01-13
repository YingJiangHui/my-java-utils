package my.servlet.java;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public class ServletSetup {
    static HashMap<String, Servlet> cache;
    public ServletSetup(){
        cache = new HashMap<String, Servlet>();
    }

    Servlet getServlet(String url) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(cache.containsKey(url)){
            return cache.get(url);
        }

        FileReader reader = new FileReader("F:\\code\\java\\my-java-utils\\src\\my\\servlet\\java\\web.properties");
        Properties properties = new Properties();
        properties.load(reader);
        Object className = properties.get(url);
        if(className == null){
            throw new RuntimeException("404");
        }
        Class<?> clazz = Class.forName(className.toString());
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Servlet servlet = (Servlet) instance;
        cache.put(url,servlet);
        return servlet;
    }
}
