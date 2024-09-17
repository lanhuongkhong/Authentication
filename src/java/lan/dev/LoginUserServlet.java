/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lan.dev;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lan.dev.data.dao.DatabaseDao;
import lan.dev.data.dao.UserDao;
import lan.dev.data.model.User;
import lan.dev.data.impl.UserImpl;
/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginUserServlet", urlPatterns = {"/LoginUserServlet"})
public class LoginUserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý nếu GET được hỗ trợ, hoặc để trống nếu chỉ POST được hỗ trợ.
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported");
    }

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String gmail = request.getParameter("gmail"); // Thay "username" bằng "gmail"
        String mat_khau = request.getParameter("mat_khau"); // Thay "password" bằng "mat_khau"

        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.checkAccount(gmail, mat_khau);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Đăng nhập</title></head>");
        out.println("<body>");

        if (user != null) {
            out.println("<h2>Đăng nhập thành công!</h2>");
            out.println("<p>Trang web sẽ chuyển hướng sau vài giây...</p>");
            out.println("<script>");
            out.println("setTimeout(function() { window.location.href = 'UserListServlet'; }, 3000);");
            out.println("</script>");
        } else {
            out.println("<h2>Đăng nhập thất bại!</h2>");
            out.println("<p>Email hoặc mật khẩu không đúng.</p>");
            out.println("<p><a href='login.html'>Thử lại</a></p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}