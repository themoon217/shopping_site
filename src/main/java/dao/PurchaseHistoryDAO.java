package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PurchaseHistory;

public class PurchaseHistoryDAO {
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

    // 購入履歴を取得するメソッド（ユーザーIDを指定）
    public List<PurchaseHistory> getPurchaseHistory(String userId) {
        List<PurchaseHistory> historyList = new ArrayList<>();
        String sql = "SELECT t.id AS transaction_id, i.id AS items_id,i.name AS item_name, s.name AS size_name, c.name AS color_name, t.orders, i.imgPath, t.price, t.date " +
                     "FROM transactions t " +
                     "JOIN items i ON t.itemID = i.id " +
                     "JOIN colors c ON t.colorID = c.id " +
                     "JOIN sizes s ON t.sizeID = s.id " +
                     "JOIN users AS u ON u.id=t.userID "+
                     "WHERE u.userID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, userId);

            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    PurchaseHistory history = new PurchaseHistory();
                    history.setTransactionId(rs.getInt("transaction_id"));
                    history.setItemId(rs.getInt("items_id"));
                    history.setItemName(rs.getString("item_name"));
                    history.setSizeName(rs.getString("size_name"));
                    history.setColorName(rs.getString("color_name"));
                    history.setOrders(rs.getInt("orders"));
                    history.setImgPath(rs.getString("imgPath"));
                    history.setPrice(rs.getInt("price"));
                    history.setDate(rs.getTimestamp("date"));

                    historyList.add(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}