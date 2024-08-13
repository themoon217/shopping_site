package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Account;
import model.GetRecommendationItems;
import model.Recommendation;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetRecommendationItems getRecommendationItems = new GetRecommendationItems();
		List<Recommendation> recomList = getRecommendationItems.execute();
		request.setAttribute("recomList", recomList);
		HttpSession session = request.getSession();
		Account userID = (Account) session.getAttribute("account");
		System.out.println(userID.getUserID());
		UserDAO dao = new UserDAO();
        int point = dao.getPoint(userID.getUserID());
        request.setAttribute("point", point);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
