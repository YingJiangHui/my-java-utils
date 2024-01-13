package my.servlet.java;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ServletSetup {
    Servlet getServlet(String url) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        FileReader reader = new FileReader("F:\\code\\java\\my-java-utils\\src\\my\\servlet\\java\\web.properties");
        Properties properties = new Properties();
        properties.load(reader);
        Object className = properties.get(url);
        System.out.println(className);
        Class<?> clazz = Class.forName(className.toString());
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Servlet servlet = (Servlet) instance;
        return servlet;
    }
}
