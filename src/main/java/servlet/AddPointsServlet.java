package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Account;

@WebServlet("/AddPointsServlet")
public class AddPointsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("pointup.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pointStr = request.getParameter("point");
        int point = 0;

        try {
            point = Integer.parseInt(pointStr);

            if (point < 0) {
                throw new IllegalArgumentException("ポイントは正の数でなければなりません。");
            }
            System.out.println("A");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ポイントは数値で入力してください。");
            RequestDispatcher rd = request.getRequestDispatcher("pointup.jsp");
            rd.forward(request, response);
            return;
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("pointup.jsp");
            rd.forward(request, response);
            return;
           
        }
        System.out.println("B");

        HttpSession session = request.getSession(false); // セッションが存在するか確認
        if (session == null) {
            request.setAttribute("errorMessage", "セッションが無効です。再度ログインしてください。");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }

        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            request.setAttribute("errorMessage", "ログイン情報が見つかりません。再度ログインしてください。");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }

        String userID = account.getUserID();
        UserDAO dao = new UserDAO();

        try {
            boolean result = dao.addPoints(userID, point);

            if (result) {
                request.setAttribute("successMessage", "ポイントが正常に追加されました。");
            } else {
                request.setAttribute("errorMessage", "ポイントの追加に失敗しました。");
            }
            System.out.println("C");
        } catch (IllegalStateException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
        System.out.println("D");
        RequestDispatcher rd = request.getRequestDispatcher("pointup.jsp");
        rd.forward(request, response);
    }
}