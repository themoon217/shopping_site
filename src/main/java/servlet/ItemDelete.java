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
import model.GetItemNames;

@WebServlet("/ItemDelete")
public class ItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<model.Item> itemList = new ArrayList<model.Item>();
		GetItemNames getItemNames = new GetItemNames();
		itemList = getItemNames.itemList();
		request.setAttribute("itemList", itemList);
		RequestDispatcher rd = request.getRequestDispatcher("itemDelete.jsp");
        rd.forward(request, response);
	}



	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        String itemName = request.getParameter("name");

	        ItemsDAO dao = new ItemsDAO();
	        int itemId = dao.getItemID(itemName);

	        if (itemId != 0) { // getItemIDが見つからなかった場合は0を返すようにしている
	            boolean deleteSuccess = dao.deleteItem(itemId);

	            if (deleteSuccess) {
	                request.setAttribute("message", "商品が削除されました");
	            } else {
	                request.setAttribute("message", "商品の削除に失敗しました");
	            }
	        } else {
	            request.setAttribute("message", "指定された商品は見つかりませんでした");
	        }

	        request.getRequestDispatcher("/deleteResult.jsp").forward(request, response);
	    }
	}


	