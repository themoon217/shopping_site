package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.AccountChange;

public class AccountChangeDAO {
    public boolean updateAccount(AccountChange accountChange) {
        String sql = "UPDATE users SET pass = ?, name = ? WHERE userID = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/moonwith?characterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, accountChange.getPass());
            pStmt.setString(2, accountChange.getName());
            pStmt.setString(3, accountChange.getUserID());
            int result = pStmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("アカウント情報の更新中にエラーが発生しました", e);
        }
    }
}