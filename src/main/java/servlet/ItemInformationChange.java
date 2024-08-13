package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import model.ColorData;
import model.GetItemData;
import model.GetItemNames;
import model.ItemData;

@WebServlet("/ItemInformationChange")
public class ItemInformationChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<model.Item> itemList = new ArrayList<model.Item>();
		GetItemNames getItemNames = new GetItemNames();
		itemList = getItemNames.itemList();
		request.setAttribute("itemList", itemList);
		RequestDispatcher rd = request.getRequestDispatcher("itemInformationChange.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemsDAO  dao = new ItemsDAO();
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int itemID = dao.getItemID(name);

		GetItemData getItemData = new GetItemData();
		ItemData itemData = getItemData.execute(itemID);
		System.out.println(itemID);
		request.setAttribute("itemID", itemID);
		request.setAttribute("itemData", itemData);
		ColorData colorData = getItemData.executeColor(itemID);
		request.setAttribute("colorData", colorData.getColorList());

		RequestDispatcher rd = request.getRequestDispatcher("changeItem.jsp");
        rd.forward(request, response);
	}

}
