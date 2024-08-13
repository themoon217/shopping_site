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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("userID");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

		if (userID == null || userID.trim().isEmpty() || pass == null || pass.trim().isEmpty() || name == null || name.trim().isEmpty()) {
			request.setAttribute("errorMessage", "全ての項目を入力してください");
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			rd.forward(request, response);
			return;
		}
		UserDAO dao = new UserDAO();
		try {
			boolean result = dao.register(userID, pass, name);
			if (result) {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		} catch (IllegalStateException e) {
			RequestDispatcher rd = request.getRequestDispatcher("RegisterNG.jsp");
			rd.forward(request, response);
		}
	}
}