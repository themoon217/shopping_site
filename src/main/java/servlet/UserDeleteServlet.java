package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("userdelete.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userID = request.getParameter("userID");
        String pass = request.getParameter("pass");
        String name = request.getParameter("name");

        UserDAO dao = new UserDAO();

        try {
            boolean result = dao.userDelete(userID, pass, name);

            if (result) {
                RequestDispatcher rd = request.getRequestDispatcher("userdeletesuccess.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("errorMessage", "入力された情報が正しくありません。");
                RequestDispatcher rd = request.getRequestDispatcher("userdelete.jsp");
                rd.forward(request, response);
            }
        } catch (IllegalStateException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("userdelete.jsp");
            rd.forward(request, response);
        }
    }
}