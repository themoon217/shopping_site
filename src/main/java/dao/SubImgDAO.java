package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.AddSubImgData;

public class SubImgDAO {
	public void insertSubImg(AddSubImgData addSubImgData) {
        System.out.println(addSubImgData.getItemID());
        System.out.println(addSubImgData.getSubImgPath());
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "INSERT INTO `subimg`(`itemID`, `imgPath`) VALUES (?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, addSubImgData.getItemID());
            pStmt.setString(2, addSubImgData.getSubImgPath());
            int result = pStmt.executeUpdate();
            if (result < 1) {
                throw new IllegalStateException("画像の追加に失敗しました");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
