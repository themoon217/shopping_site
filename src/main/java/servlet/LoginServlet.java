package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Account;
import model.Login;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        RequestDispatcher rd = request.getRequestDispatcher("loginNG.jsp");
	        rd.forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        String userID = request.getParameter("userID");
	        String pass = request.getParameter("pass");

	        UserDAO dao = new UserDAO();
	        Login login = new Login(userID, pass);
	        Account account = dao.findByLogin(login);

	        if (account != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("account", account);
	            System.out.println("Login successful: " + account.getUserID());
	            // ユーザー情報を取得してチェック
	            if ("owner".equals(userID) && "owner".equals(pass)) {
	                response.sendRedirect("ownerMain.jsp"); // 条件が合致した場合、ownerMain.jspへリダイレクト
	            } else {
	                response.sendRedirect("success-start-shopping.jsp"); // 通常のリダイレクト先
	            }
	        } else {
	            response.sendRedirect("loginNG.jsp"); // ログイン失敗時に loginNG.jsp へリダイレクト
	        }
	    }
	}