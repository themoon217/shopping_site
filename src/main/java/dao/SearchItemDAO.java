package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class SearchItemDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/moonwith?characterEncoding=UTF-8&serverTimezone=JST";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバが見つかりません", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public List<Item> searchItems(int categoryId, int sex) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT items.id, items.name, items.imgPath, items.price, "
                + "GROUP_CONCAT(DISTINCT colors.name SEPARATOR ', ') AS colorList "
                + "FROM items "
                + "JOIN items_categories ON items.id = items_categories.itemID "
                + "JOIN categories ON items_categories.categoryID = categories.id "
                + "LEFT JOIN items_colors ON items.id = items_colors.itemID "
                + "LEFT JOIN colors ON items_colors.colorID = colors.id "
                + "WHERE items_categories.categoryID = ? "
                + "AND items_categories.itemID IN ("
                + "  SELECT itemID FROM items_categories "
                + "  WHERE categoryID = ? "
                + ") "
                + "GROUP BY items.id";

        // デバッグ用の出力
        System.out.println("SQL Query: " + sql);
        System.out.println("Category ID: " + categoryId);
        System.out.println("Sex: " + sex);
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, sex); // 性別に基づくカテゴリ ID

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setImgPath(rs.getString("imgPath"));
                    item.setPrice(rs.getInt("price"));
                    item.setColors(rs.getString("colorList"));
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    public List<Item> searchItemsByKeyword(String keyword) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT items.id, items.name, items.imgPath, items.price, "
                + "GROUP_CONCAT(DISTINCT colors.name SEPARATOR ', ') AS colorList "
                + "FROM items "
                + "LEFT JOIN items_colors ON items.id = items_colors.itemID "
                + "LEFT JOIN colors ON items_colors.colorID = colors.id "
                + "WHERE items.name LIKE ? "
                + "GROUP BY items.id";

        System.out.println("SQL Query (Keyword): " + sql);
        System.out.println("Keyword: " + keyword);

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setImgPath(rs.getString("imgPath"));
                    item.setPrice(rs.getInt("price"));
                    item.setColors(rs.getString("colorList"));
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}