import DB.DBmanage;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MIServerTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("请求收到");
        String user = request.getParameter("user"); // 从 request 中获取名为 account 的参数的值
        String result = "Login False!";
//        if (DBmanage.initUser(new User("12as12123", "121223"))) {
//        if (DBmanage.checkUser() != null) {
        if (DBmanage.add(new User("321313131", "21313"))) {
            result = "用户添加成功!";
        } else {
            result = "用户添加失败!";
        }
        resp.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式
        PrintWriter pw = resp.getWriter(); // 获取 response 的输出流
        pw.println(result); // 通过输出流把业务逻辑的结果输出
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
