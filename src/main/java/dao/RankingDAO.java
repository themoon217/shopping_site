package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.RankingItem;
public class RankingDAO {
    // JDBCドライバの読み込み
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/moonwith?characterEncoding=UTF-8&serverTimezone=JST", "root", "");
    }
 // 売上ランキングを取得するメソッド（カテゴリIDを指定）
    public List<RankingItem> getTopItemsByCategory(int categoryID) {
        List<RankingItem> topItems = new ArrayList<>();

        String sql = "SELECT i.id, i.name, i.imgPath, i.price, SUM(t.orders) as totalSales " +
                "FROM items i " +
                "JOIN transactions t ON i.id = t.itemID " +
                "JOIN items_categories ic ON i.id = ic.itemID " +
                "WHERE ic.categoryID = ? " +
                "GROUP BY i.id, i.name, i.imgPath, i.price " +
                "ORDER BY totalSales DESC " +
                "LIMIT 3";

        try (Connection conn = getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1, categoryID);

            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setImgPath(rs.getString("imgPath"));
                    item.setPrice(rs.getInt("price"));

                    int totalSales = rs.getInt("totalSales");

                    RankingItem rankingItem = new RankingItem(item, totalSales);
                    topItems.add(rankingItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topItems;
    }
}