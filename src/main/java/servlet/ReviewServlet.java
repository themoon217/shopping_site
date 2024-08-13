package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewsDAO;
import model.Account;


@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // フォームからパラメータを取得
        String evaluationStr = request.getParameter("evaluation");
        String comment = request.getParameter("review");
        String itemIDStr = request.getParameter("itemID");

        // パラメータのログ出力（デバッグ用）
        System.out.println("Evaluation Parameter: " + evaluationStr);
        System.out.println("Comment Parameter: " + comment);
        System.out.println("Item ID Parameter: " + itemIDStr);

        // パラメータの検証
        if (evaluationStr == null || itemIDStr == null || evaluationStr.isEmpty() || itemIDStr.isEmpty() || comment == null || comment.isEmpty()) {
            System.out.println("Error: Missing evaluation, itemID, or comment.");
            request.setAttribute("errorMsg", "評価、商品ID、またはコメントが指定されていません。");
            request.getRequestDispatcher("review.jsp?id=" + itemIDStr).forward(request, response);
            return;
        }

        int evaluation;
        int itemID;

        try {
            evaluation = Integer.parseInt(evaluationStr);
            itemID = Integer.parseInt(itemIDStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid format for evaluation or itemID.");
            request.setAttribute("errorMsg", "評価または商品IDの形式が不正です。");
            request.getRequestDispatcher("review.jsp?id=" + itemIDStr).forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            System.out.println("Error: User is not logged in.");
            request.setAttribute("errorMsg", "ログインが必要です。");
            request.getRequestDispatcher("review.jsp?id=" + itemIDStr).forward(request, response);
            return;
        }

        // ReviewsDAOをインスタンス化してレビューをデータベースに保存
        ReviewsDAO reviewsDAO = new ReviewsDAO();
        boolean isSuccess = reviewsDAO.reviews(evaluation, comment, itemID, user.getUserID());

        if (isSuccess) {
            // 成功した場合の処理（成功メッセージを表示）
            System.out.println("Review posted successfully.");
            request.setAttribute("successMsg", "レビューの投稿に成功しました");
            request.getRequestDispatcher("review.jsp?id=" + itemIDStr).forward(request, response);
        } else {
            // 失敗した場合の処理（エラーメッセージを表示）
            System.out.println("Error: Failed to save review to the database.");
            request.setAttribute("errorMsg", "データベースへの保存に失敗しました");
            request.getRequestDispatcher("review.jsp?id=" + itemIDStr).forward(request, response);
        }
    }
}