package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Cart;

public class Transaction {	
	private final String JDBC_URL="jdbc:mysql://localhost:3306/moonwith?characterEncoding=UTF-8&serverTimezone=JST";
	private final String DB_USER="root";
	private final String DB_PASS="";
	
	public void TransactionDO(List<Cart> cartList) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            conn.setAutoCommit(false); // Start transaction

            int cartTotalPrice = 0;
            CartDAO idgetter = new CartDAO();
            String theID = cartList.get(0).getUserID();
            int userNum = idgetter.getUserIDsql(theID);

            for (Cart cart : cartList) {
                int itemID = cart.getItemID();
                int sizeID = cart.getSizeID();
                int colorID = cart.getColorID();
                int orders = cart.getOrders();
                int price = cart.getPrice();

                updateStocks(conn, itemID, sizeID, colorID, orders);
                cartTotalPrice += (price * orders);

                // Insert into transaction history
                intoHistory(conn, userNum, itemID, colorID, sizeID, orders, price);
            }

            // Update user's point
            updateMoney(conn, userNum, cartTotalPrice);

            // Delete cart entries
            idgetter.deleteCartALL(userNum);

            conn.commit(); // Commit transaction if all operations succeed
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction if any operation fails
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto-commit to true
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    private void intoHistory(Connection conn, int userNum, int itemID, int colorID, int sizeID, int orders, int price) throws SQLException {
        String sqlInsertHistory = "INSERT INTO transactions (userID, itemID, colorID, sizeID, orders, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertHistory)) {
            pstmt.setInt(1, userNum);
            pstmt.setInt(2, itemID);
            pstmt.setInt(3, colorID);
            pstmt.setInt(4, sizeID);
            pstmt.setInt(5, orders);
            pstmt.setInt(6, price);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("History updated successfully");
            } else {
                System.out.println("No rows updated for History");
            }
        }
    }

    private void updateMoney(Connection conn, int userID, int cartTotalPrice) throws SQLException {
        String sqlUpdateMoney = "UPDATE users SET point = point - ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdateMoney)) {
            pstmt.setInt(1, cartTotalPrice);
            pstmt.setInt(2, userID);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Point updated successfully");
            } else {
                System.out.println("No rows updated for point update");
            }
        }
    }

    private void updateStocks(Connection conn, int itemID, int sizeID, int colorID, int orders) throws SQLException {
        String sqlUpdate = "UPDATE stocks SET stock = stock - ? WHERE itemID = ? AND sizeID = ? AND colorID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setInt(1, orders);
            pstmt.setInt(2, itemID);
            pstmt.setInt(3, sizeID);
            pstmt.setInt(4, colorID);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully for ItemID: " + itemID +
                        ", SizeID: " + sizeID + ", ColorID: " + colorID);
            } else {
                System.out.println("No rows updated for ItemID: " + itemID +
                        ", SizeID: " + sizeID + ", ColorID: " + colorID);
            }
        }
    }
	
	        
}