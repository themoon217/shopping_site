package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import model.GetRecommendationItems;
import model.Recommendation;

@WebServlet("/RecommendationServlet")
public class RecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Recommendation> recommItem = new ArrayList<>();
		List<Recommendation> notRecommItem = new ArrayList<>();
		GetRecommendationItems getRecommendationItems = new GetRecommendationItems();
		recommItem = getRecommendationItems.execute();
		notRecommItem = getRecommendationItems.executeNot();
		request.setAttribute("recommItem", recommItem);
		request.setAttribute("notRecommItem", notRecommItem);
		RequestDispatcher rd = request.getRequestDispatcher("recommendationItem.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String itemName = request.getParameter("notname");
		if (itemName != null) {
			ItemsDAO dao = new ItemsDAO();
			System.out.println(itemName);
			dao.addRecommendation(itemName);
		}
		RequestDispatcher rd = request.getRequestDispatcher("recommendChanged.jsp");
        rd.forward(request, response);
	}
}
