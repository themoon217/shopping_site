package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.IntoCartBO;

@WebServlet("/IntoCart")
public class IntoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("intoCart.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String itemIDStr = request.getParameter("id");
		int itemID = Integer.parseInt(itemIDStr);
		Account userID = (Account) session.getAttribute("account");
		IntoCartMethod intoCartMethod = new IntoCartMethod();

		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String numberStr = request.getParameter("number");
		int number = Integer.parseInt(numberStr);

		IntoCartBO intoCart = new IntoCartBO(userID.getUserID(), itemID, color, size, number);
		boolean done = intoCartMethod.execute(intoCart);
		RequestDispatcher rd = request.getRequestDispatcher("intoCart.jsp");
        rd.forward(request, response);
	}
}
