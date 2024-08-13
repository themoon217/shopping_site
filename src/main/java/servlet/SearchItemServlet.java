package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SearchItemDAO;
import model.Item;

@WebServlet("/SearchItemServlet")
public class SearchItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // リクエストからパラメータを取得
        String sexStr = request.getParameter("sex");
        String partStr = request.getParameter("categoryId");
        String keyword = request.getParameter("keyword");

        System.out.println("Sex: " + sexStr);
        System.out.println("Category ID: " + partStr);
        System.out.println("Keyword: " + keyword);

        SearchItemDAO dao = new SearchItemDAO();
        List<Item> items = null;

        // キーワード検索の処理
        if (keyword != null && !keyword.isEmpty()) {
            // キーワード検索
            items = dao.searchItemsByKeyword(keyword);
        } else if (sexStr != null && !sexStr.isEmpty() && partStr != null && !partStr.isEmpty()) {
            // カテゴリ検索
            int sex = Integer.parseInt(sexStr);
            int part = Integer.parseInt(partStr);
            items = dao.searchItems(part, sex);
        } else {
            // パラメータが不足している場合
            request.setAttribute("errorMessage", "キーワードを入力するか、性別とカテゴリを選択してください。");
            RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
            rd.forward(request, response);
            return;
        }

        // 検索結果をリクエストにセット
        request.setAttribute("items", items);

        // 検索結果のページへフォワード
        RequestDispatcher rd = request.getRequestDispatcher("searchResults.jsp");
        rd.forward(request, response);
    }
}