package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryData;
import model.ColorData;
import model.GetItemData;
import model.ItemData;
import model.Review;
import model.Subimg;

@WebServlet("/Item")
public class Item extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Item servlet called");
        String itemIDStr = request.getParameter("id");
        System.out.println("Received itemIDStr: " + itemIDStr);  // デバッグ用

        if (itemIDStr == null || itemIDStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Item ID is missing.");
            return;
        }

        int itemID;
        try {
            itemID = Integer.parseInt(itemIDStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Item ID format.");
            return;
        }

        request.setAttribute("itemID", itemID);

        GetItemData getItemData = new GetItemData();
        ItemData itemData = getItemData.execute(itemID);

        if (itemData == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found.");
            return;
        }

        request.setAttribute("itemData", itemData);

        // デバッグログ出力
        System.out.println("ItemData imgPath: " + itemData.getImgPath());

        CategoryData categoryData = getItemData.executeCategory(itemID);
        request.setAttribute("categoryData", categoryData);

        ColorData colorData = getItemData.executeColor(itemID);
        request.setAttribute("colorData", colorData);

        Subimg subimg = getItemData.executeImg(itemID);
        request.setAttribute("subimg", subimg);

        // レビューを取得してリクエスト属性に設定
        List<Review> reviews = getItemData.executeReviews(itemID);
        System.out.println("Number of reviews: " + reviews.size());  // デバッグ用
        for (Review review : reviews) {
            System.out.println("Review by " + review.getUserId() + ": " + review.getComment() + " (" + review.getEvaluation() + " stars)");
        }
        request.setAttribute("reviews", reviews);

        RequestDispatcher rd = request.getRequestDispatcher("item.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}