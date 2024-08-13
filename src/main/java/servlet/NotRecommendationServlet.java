package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;

@WebServlet("/NotRecommendationServlet")
public class NotRecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if (name != null) {
			ItemsDAO dao = new ItemsDAO();
			System.out.println(name);
			dao.deleteRecommendation(name);
		}
		RequestDispatcher rd = request.getRequestDispatcher("recommendChanged.jsp");
        rd.forward(request, response);
	}

}
