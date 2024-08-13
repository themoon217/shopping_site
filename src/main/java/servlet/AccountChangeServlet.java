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
import model.AccountChange;

@WebServlet("/AccountChangeServlet")
public class AccountChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("accountchange.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String newPass = request.getParameter("pass");
        String newName = request.getParameter("name");

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

        AccountChange accountChange = new AccountChange(userID, newPass, newName);
        try {
            boolean result = dao.updateAccount(accountChange);

            if (result) {
                request.setAttribute("successMessage", "アカウント情報が正常に更新されました。");
            } else {
                request.setAttribute("errorMessage", "アカウント情報の更新に失敗しました。");
            }
        } catch (IllegalStateException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("accountchange.jsp");
        rd.forward(request, response);
    }
}