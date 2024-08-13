package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

public class ReviewsDAO {
    public List<Review> getReviews(int itemId) {
        List<Review> reviews = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT r.userID, r.evaluation, r.comment, u.userID AS userName FROM reviews AS r JOIN users AS u ON r.userID = u.id WHERE r.itemID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemId);
            ResultSet rs = pStmt.executeQuery();
            // デバッグ出力
            System.out.println("Executing query for itemID: " + itemId);
            
            while (rs.next()) {
                String userId = rs.getString("userName");  // usersテーブルのuserIDを取得
                int evaluation = rs.getInt("evaluation");
                String comment = rs.getString("comment");
                
                // デバッグ出力
                System.out.println("Found review: userID=" + userId + ", evaluation=" + evaluation + ", comment=" + comment);
                
                reviews.add(new Review(evaluation, comment, itemId, userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    

    public boolean reviews(int evaluation, String comment, int itemID, String userID) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            // ユーザーIDを整数に変換
            int userIDInt = getUserIDFromUserID(userID, conn);

            if (userIDInt == -1) {
                System.out.println("Error: User ID not found.");
                return false;
            }

            String sql = "INSERT INTO reviews (evaluation, comment, itemID, userID) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, evaluation);
            stmt.setString(2, comment);
            stmt.setInt(3, itemID);
            stmt.setInt(4, userIDInt);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getUserIDFromUserID(String userID, Connection conn) {
        try {
            String sql = "SELECT id FROM users WHERE userID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}