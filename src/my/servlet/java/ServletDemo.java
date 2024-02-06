package my.servlet.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Scanner;

public class ServletDemo {
    static String getFakeWebUrl(){
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        return url;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ServletSetup servletSetup = new ServletSetup();
        while (true){
            System.out.println("输入想要请求的资源");
            String url = getFakeWebUrl();
            try{
                Servlet servlet = servletSetup.getServlet(url);
                servlet.service();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }

        }

    }
}
