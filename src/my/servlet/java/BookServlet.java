package my.servlet.java;

public class BookServlet implements Servlet {

    @Override
    public void service() {
        System.out.println("执行书籍服务");
    }
}
