package cn.edu.usts.cs2019.servlet;

import cn.edu.usts.cs2019.dao.CustomerDaoImpl;
import cn.edu.usts.cs2019.dao.base.ICustomerDao;
import cn.edu.usts.cs2019.entity.Customer;
import cn.edu.usts.cs2019.service.CustomerServiceImpl;
import cn.edu.usts.cs2019.service.base.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns="/servlet/hello")
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("收到客户端的get请求!");
        // 1、接收用户发送来的具体请求
        String userName = request.getParameter("username");



        String userPass = request.getParameter("password");
        System.out.println("userName:" + userName);     
        System.out.println("userPass:" + userPass);
        // 2、Servlet业务处理代码
        // 其他处理代码,如用户登录功能中，对用户身份进行验证；查询功能中，根据请求获取查询结果；
        //User user = new User("Tom","1234");
        ICustomerDao cDao = new CustomerDaoImpl();
        ICustomerService service = new CustomerServiceImpl(cDao);
        //service.insert(user);

        Customer customer = new Customer(userName, userPass);
        customer = service.login(customer);  // 处理登录请求

        // 3、处理完成后，可以直接向浏览器返回结果，或转发请求。
        // 处理示例，或转发请求。(要求success.jsp文件预先准备好)
        if ( customer != null) {
            response.sendRedirect("../success.jsp");
            HttpSession session = request.getSession(); // 获取当前会话
            session.setAttribute("UserName", userName);  // 写入会话中的属性UserName中。该属性在success.jsp中获取。
            session.setAttribute("id",customer.getId());
            session.setAttribute("telphone",customer.getTelphone());
            session.setAttribute("address",customer.getAddress());
            session.setAttribute("company",customer.getCompany());


        }
        else {
            response.sendRedirect("../failure.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}