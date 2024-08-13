package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockDAO;
import model.CheckStockData;
import model.CheckStockMethod;

@WebServlet("/AddStock")
public class AddStock extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemIDStr = request.getParameter("itemID");
		String colorIDStr = request.getParameter("color");
		int itemID = Integer.parseInt(itemIDStr);
		int colorID = Integer.parseInt(colorIDStr);
		
		CheckStockMethod csm = new CheckStockMethod();
        CheckStockData checkStockData = csm.executeStock(itemID, colorID);
        request.setAttribute("checkStockData", checkStockData);
        RequestDispatcher rd = request.getRequestDispatcher("addStock.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemIDstr = request.getParameter("itemID");
		String colorIDstr = request.getParameter("colorID");
		String Sstr = request.getParameter("S");
		String Mstr = request.getParameter("M");
		String Lstr = request.getParameter("L");
		String XLstr = request.getParameter("XL");
		int itemID = Integer.parseInt(itemIDstr);
		int colorID = Integer.parseInt(colorIDstr);
		int S = Integer.parseInt(Sstr);
		int M = Integer.parseInt(Mstr);
		int L = Integer.parseInt(Lstr);
		int XL = Integer.parseInt(XLstr);
		CheckStockData checkStockData = new CheckStockData("", "", "", S, M, L, XL, itemID, colorID);
		StockDAO dao = new StockDAO();
		dao.AddStock(checkStockData);
		RequestDispatcher rd = request.getRequestDispatcher("ownerMain.jsp");
        rd.forward(request, response);
	}

}
