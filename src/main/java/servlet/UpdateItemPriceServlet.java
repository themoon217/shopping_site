package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;

/**
 * Servlet implementation class UpdateItemPriceServlet
 */
@WebServlet("/UpdateItemPriceServlet")
public class UpdateItemPriceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int itemID = Integer.parseInt(request.getParameter("itemID"));
        double newPrice = Double.parseDouble(request.getParameter("price"));

        ItemsDAO dao = new ItemsDAO();
        boolean success = dao.updateItemPrice(itemID, newPrice);

        // 成功の場合は結果ページにリダイレクト
        if (success) {
            response.sendRedirect("itemInformationChangeResult.jsp?success=true");
        } else {
            // 失敗の場合は結果ページにリダイレクト
            response.sendRedirect("itemInformationChangeResult.jsp?success=false");
        }
    }
}