package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CheckStockData;

public class StockDAO {
    public ArrayList<CheckStockData> getAllItemStockDAO() {
        ArrayList<CheckStockData> checkStockData = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql ="SELECT i.id AS itemID, c.id AS colorID, i.name AS itemName, i.imgPath, c.name AS color, \r\n" + //
                        "(SELECT st.stock FROM stocks AS st WHERE st.itemID = i.id AND st.colorID = c.id AND st.sizeID = 1) AS S,\r\n" + //
                        "(SELECT st.stock FROM stocks AS st WHERE st.itemID = i.id AND st.colorID = c.id AND st.sizeID = 2) AS M,\r\n" + //
                        "(SELECT st.stock FROM stocks AS st WHERE st.itemID = i.id AND st.colorID = c.id AND st.sizeID = 3) AS L,\r\n" + //
                        "(SELECT st.stock FROM stocks AS st WHERE st.itemID = i.id AND st.colorID = c.id AND st.sizeID = 4) AS XL\r\n" + //
                        "FROM stocks AS s\r\n" + //
                        "JOIN items AS i\r\n" + //
                        "ON s.itemID = i.id\r\n" + //
                        "JOIN colors AS c\r\n" + //
                        "ON c.id = s.colorID\r\n" + //
                        "GROUP BY i.imgPath, c.name";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String itemName = rs.getString("itemName");
                String imgPath = rs.getString("imgPath");
                String color = rs.getString("color");
                int s = rs.getInt("S");
                int m = rs.getInt("M");
                int l = rs.getInt("L");
                int xl = rs.getInt("XL");
                int itemID = rs.getInt("itemID");
                int colorID = rs.getInt("colorID");
                CheckStockData stock = new CheckStockData(itemName, imgPath, color, s, m, l, xl, itemID, colorID);
                checkStockData.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkStockData;
    }

    public CheckStockData getItemStockDAO(int itemID, int colorID) {
        CheckStockData checkStockData = new CheckStockData();
        int[] stock = {0, 0, 0, 0};
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql ="SELECT stock FROM stocks WHERE itemID = ? AND colorID = ? ORDER BY sizeID";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemID);
            pStmt.setInt(2, colorID);
            ResultSet rs = pStmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                stock[i] = rs.getInt("stock");
                i++;
            }
            sql ="SELECT `name`, `imgPath` FROM `items` WHERE id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemID);
            rs = pStmt.executeQuery();
            String itemName = "";
            String imgPath = "";
            while (rs.next()) {
                itemName = rs.getString("name");
                imgPath = rs.getString("imgPath");
            }
            sql ="SELECT `name` FROM `colors` WHERE id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, colorID);
            rs = pStmt.executeQuery();
            String color = "";
            while (rs.next()) {
                color = rs.getString("name");
            }
            checkStockData = new CheckStockData(itemName, imgPath, color, stock[0], stock[1], stock[2], stock[3], itemID, colorID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkStockData;
    }

    public void AddStock(CheckStockData checkStockData) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした", e);
        }
    
        String sql = "UPDATE stocks SET stock = ? WHERE itemID = ? AND colorID = ? AND sizeID = ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            conn.setAutoCommit(false); // トランザクションを開始
    
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                // sizeID = 1 (S)
                pStmt.setInt(1, checkStockData.getS());
                pStmt.setInt(2, checkStockData.getItemID());
                pStmt.setInt(3, checkStockData.getColorID());
                pStmt.setInt(4, 1);
                pStmt.executeUpdate();
    
                // sizeID = 2 (M)
                pStmt.setInt(1, checkStockData.getM());
                pStmt.setInt(2, checkStockData.getItemID());
                pStmt.setInt(3, checkStockData.getColorID());
                pStmt.setInt(4, 2);
                pStmt.executeUpdate();
    
                // sizeID = 3 (L)
                pStmt.setInt(1, checkStockData.getL());
                pStmt.setInt(2, checkStockData.getItemID());
                pStmt.setInt(3, checkStockData.getColorID());
                pStmt.setInt(4, 3);
                pStmt.executeUpdate();
    
                // sizeID = 4 (XL)
                pStmt.setInt(1, checkStockData.getXl());
                pStmt.setInt(2, checkStockData.getItemID());
                pStmt.setInt(3, checkStockData.getColorID());
                pStmt.setInt(4, 4);
                pStmt.executeUpdate();
    
                conn.commit(); // トランザクションをコミット
            } catch (SQLException e) {
                conn.rollback(); // エラーが発生した場合はロールバック
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
