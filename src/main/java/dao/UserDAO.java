package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.AccountChange;
import model.Login;

public class UserDAO {
	 private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?characterEncoding=UTF-8&serverTimezone=JST", "root", "");
	    }

	    public boolean register(String userID, String pass, String name) {
	        try {
	            // 古いバージョンの JDBC ドライバクラス名
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new IllegalStateException("JDBCドライバが見つかりません", e);
	        }

	        String sql = "INSERT INTO users(userID, pass, name, date) VALUES (?, ?, ?, NOW())";
	        try (Connection conn = getConnection();
	             PreparedStatement pStmt = conn.prepareStatement(sql)) {
	            if (isUserIdExist(conn, userID)) {
	                throw new IllegalStateException("ユーザーIDが既に存在します");
	            }
	            pStmt.setString(1, userID);
	            pStmt.setString(2, pass);
	            pStmt.setString(3, name);
	            int result = pStmt.executeUpdate();
	            if (result != 1) {
	                throw new IllegalStateException("ユーザーの登録に失敗しました");
	            }
	        } catch (SQLException e) {
	            throw new IllegalStateException("ユーザーの登録中にエラーが発生しました", e);
	        }
	        return true;
	    }

	    private boolean isUserIdExist(Connection conn, String userID) throws SQLException {
	        String sql = "SELECT COUNT(*) FROM users WHERE userID = ?";
	        try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
	            pStmt.setString(1, userID);
	            try (ResultSet rs = pStmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        }
	        return false;
	    }

	    public Account findByLogin(Login login) {
	        Account account = null;
	        try {
	            // 古いバージョンの JDBC ドライバクラス名
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new IllegalStateException("JDBCドライバが見つかりません", e);
	        }

	        String sql = "SELECT userID, pass FROM users WHERE userID = ? AND pass = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pStmt = conn.prepareStatement(sql)) {
	            pStmt.setString(1, login.getUserID());
	            pStmt.setString(2, login.getPass());
	            try (ResultSet rs = pStmt.executeQuery()) {
	                if (rs.next()) {
	                    String userID = rs.getString("userID");
	                    String pass = rs.getString("pass");
	                    account = new Account(userID, pass);
	                }
	            }
	        } catch (SQLException e) {
	            throw new IllegalStateException("データベース操作中にエラーが発生しました", e);
	        }
	        return account;
	    }

	    public boolean updateAccount(AccountChange accountChange) {
	        String sql = "UPDATE users SET pass = ?, name = ? WHERE userID = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pStmt = conn.prepareStatement(sql)) {
	            pStmt.setString(1, accountChange.getPass());
	            pStmt.setString(2, accountChange.getName());
	            pStmt.setString(3, accountChange.getUserID());
	            int result = pStmt.executeUpdate();
	            return result == 1;
	        } catch (SQLException e) {
	            throw new IllegalStateException("アカウント情報の更新中にエラーが発生しました", e);
	        }
	    }

	    public boolean addPoints(String userID, int point) {
	        String sql = "UPDATE users SET point = point + ? WHERE userID = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pStmt = conn.prepareStatement(sql)) {
	            pStmt.setInt(1, point);
	            pStmt.setString(2, userID);
	            int result = pStmt.executeUpdate();
	            return result == 1;
	        } catch (SQLException e) {
	            throw new IllegalStateException("ポイントの追加中にエラーが発生しました", e);
	        }
	    }

	    public int getPoint(String userID) {
	        String sql = "SELECT point FROM users WHERE userID = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pStmt = conn.prepareStatement(sql)) {
	            pStmt.setString(1, userID);
	            try (ResultSet rs = pStmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("point");
	                }
	            }
	        } catch (SQLException e) {
	            throw new IllegalStateException("ポイントの取得中にエラーが発生しました", e);
	        }
	        return 0; // デフォルト値
	    }
	    public boolean userDelete(String userID, String pass, String name) {
	            String sql = "UPDATE users SET registered = 0 WHERE userID = ? AND pass = ? AND name = ? AND registered = 1";
	            try (Connection conn = getConnection();
	            PreparedStatement pStmt = conn.prepareStatement(sql)){
	            pStmt.setString(1, userID);
	            pStmt.setString(2, pass);
	            pStmt.setString(3, name);
	            int result = pStmt.executeUpdate();

	            return result == 1;
	        } catch (SQLException e) {
	            throw new IllegalStateException("ユーザーの退会中にエラーが発生しました", e);
	        }
	    }

	}