package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RankingDAO;
import model.RankingItem;

@WebServlet("/RankingServlet")
public class RankingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RankingDAO rankingDAO = new RankingDAO();
        
        List<RankingItem> menTopItems = rankingDAO.getTopItemsByCategory(1);
        List<RankingItem> womenTopItems = rankingDAO.getTopItemsByCategory(2);
        
        // 順位計算
        Map<RankingItem, Integer> menRankMap = createRankMap(menTopItems);
        Map<RankingItem, Integer> womenRankMap = createRankMap(womenTopItems);
        
        // 順位とアイテムリストをリクエストに設定
        request.setAttribute("menTopItems", menTopItems);
        request.setAttribute("womenTopItems", womenTopItems);
        request.setAttribute("menRankMap", menRankMap);
        request.setAttribute("womenRankMap", womenRankMap);

        RequestDispatcher rd = request.getRequestDispatcher("rankings.jsp");
        rd.forward(request, response);
    }
    
    private Map<RankingItem, Integer> createRankMap(List<RankingItem> items) {
        Map<RankingItem, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            rankMap.put(items.get(i), i + 1);
        }
        return rankMap;
    }
}