package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PurchaseHistoryDAO;
import model.Account;
import model.PurchaseHistory;

@WebServlet("/PurchaseHistoryServlet")
public class PurchaseHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            String userId = ((Account) session.getAttribute("account")).getUserID();
            System.out.println("UserID: " + userId); // デバッグ情報

            PurchaseHistoryDAO dao = new PurchaseHistoryDAO();
            List<PurchaseHistory> purchaseHistoryList = dao.getPurchaseHistory(userId);

            // デバッグ情報: 購入履歴の数を表示
            System.out.println("Number of purchase history records: " + purchaseHistoryList.size());

            // 各購入履歴の内容を表示
            for (PurchaseHistory history : purchaseHistoryList) {
                System.out.println("Transaction ID: " + history.getTransactionId());
                System.out.println("Item Name: " + history.getItemName());
                System.out.println("Size: " + history.getSizeName());
                System.out.println("Color: " + history.getColorName());
                System.out.println("Orders: " + history.getOrders());
                System.out.println("Image Path: " + history.getImgPath());
                System.out.println("Price: " + history.getPrice());
                System.out.println("Date: " + history.getDate());
            }

            request.setAttribute("purchaseHistory", purchaseHistoryList);
            request.getRequestDispatcher("purchaseHistory.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}