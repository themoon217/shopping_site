package dao;

import java.sql.Connection;
import java.sql.DriverManager;
//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;// <<<
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.IntoCartBO;

//refer to sql 222p, 226p for calc/mult selection
public class CartDAO {		
	private final String JDBC_URL="jdbc:mysql://localhost:3306/moonwith?characterEncoding=UTF-8&serverTimezone=JST";
	private final String DB_USER="root";
	private final String DB_PASS="";
	
	public List<Cart> findAll(String userID) {
		System.out.println("CartDAO.java Start");
		System.out.println(userID);
	    List<Cart> cartList = new ArrayList<>();

	    try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
		    String UserNum= "";
		    String sqlUserNum= "SELECT id FROM users WHERE userID= ?";
	        
	        	 try (PreparedStatement pstmtUserNum = conn.prepareStatement(sqlUserNum)){
	                 pstmtUserNum.setString(1, userID);
	                 try (ResultSet rsUserNum = pstmtUserNum.executeQuery()) {
	                     if ( rsUserNum.next()) {
	                         UserNum =  rsUserNum.getString("id");
	                     }
	                 }
	             }
        	userID=UserNum;
        	System.out.println(userID);
        	String sql = "SELECT userID, itemID, colorID, sizeID, orders FROM carts WHERE userID = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, userID);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    String dbUserID = rs.getString("userID");
	                    int itemID = rs.getInt("itemID");
	                    int colorID = rs.getInt("colorID");
	                    int sizeID = rs.getInt("sizeID");
	                    int orders = rs.getInt("orders");
	                    System.out.println("1 instance of cart loaded from mySQL in CartDAO");
	                    // Fetch item details from items table
	                    String itemName = "";
	                    int price = 0;
	                    String imgPath = "";
	                    
	                    
	                    String sqlItem = "SELECT name, price, imgPath FROM items WHERE id = ?";
	                    try (PreparedStatement pstmtItem = conn.prepareStatement(sqlItem)) {
	                        pstmtItem.setInt(1, itemID);
	                        try (ResultSet rsItem = pstmtItem.executeQuery()) {
	                            if (rsItem.next()) {
	                                itemName = rsItem.getString("name");
	                                price = rsItem.getInt("price");
	                                imgPath = rsItem.getString("imgPath");
	                            }
	                        }
	                    }
                                                                                                                                                                                                                                       
	                    // Fetch color name from colors table
	                    String colorName = "";
	                    String sqlColor = "SELECT name FROM colors WHERE id = ?";
	                    try (PreparedStatement pstmtColor = conn.prepareStatement(sqlColor)) {
	                        pstmtColor.setInt(1, colorID);
	                        try (ResultSet rsColor = pstmtColor.executeQuery()) {
	                            if (rsColor.next()) {
	                                colorName = rsColor.getString("name");
	                            }
	                        }
	                    }

	                    // Fetch size name from sizes table
	                    String sizeName = "";
	                    String sqlSize = "SELECT name FROM sizes WHERE id = ?";
	                    try (PreparedStatement pstmtSize = conn.prepareStatement(sqlSize)) {
	                        pstmtSize.setInt(1, sizeID);
	                        try (ResultSet rsSize = pstmtSize.executeQuery()) {
	                            if (rsSize.next()) {
	                                sizeName = rsSize.getString("name");
	                            }
	                        }
	                    }

	                    // Fetch stock from stocks table
	                    int stock = 0;
	                    String sqlStock = "SELECT stock FROM stocks WHERE itemID = ? AND colorID = ? AND sizeID = ?";
	                    try (PreparedStatement pstmtStock = conn.prepareStatement(sqlStock)) {
	                        pstmtStock.setInt(1, itemID);
	                        pstmtStock.setInt(2, colorID);
	                        pstmtStock.setInt(3, sizeID);
	                        try (ResultSet rsStock = pstmtStock.executeQuery()) {
	                            if (rsStock.next()) {
	                                stock = rsStock.getInt("stock");
	                            }
	                        }
	                    }
	                    
	                    System.out.println("information acquired from MySQL:");
	                    System.out.println(dbUserID);
	                    System.out.println( itemID);
	                    System.out.println(colorID);
	                    System.out.println(sizeID);
	                    System.out.println(orders);
	                    System.out.println(itemName);
	                    System.out.println(price);
	                    System.out.println(imgPath);
	                    System.out.println(stock);
	                    System.out.println(colorName);
	                    System.out.println(sizeName);
	                    // Create Cart object and add to cartList
	                    Cart cartItem = new Cart(dbUserID, itemID, colorID, sizeID, orders, itemName, price, imgPath, stock, colorName, sizeName);
	                    cartList.add(cartItem);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cartList;
	}

	public boolean deleteCartItem(int itemID, int sizeID, int colorID) { 
	
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
        	String itemDel = "DELETE FROM carts WHERE itemID = ? AND sizeID = ? AND colorID = ?";
            PreparedStatement pStmtDLT = conn.prepareStatement(itemDel);
            pStmtDLT.setInt(1, itemID);
            pStmtDLT.setInt(2, sizeID);
            pStmtDLT.setInt(3, colorID);
            int result = pStmtDLT.executeUpdate();
			if (result != 1) {
				return false;
			}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
	
	public void deleteCartALL(int userID) { 
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
        	String itemDel = "DELETE FROM carts WHERE userID = ?";
            PreparedStatement pStmtDLT = conn.prepareStatement(itemDel);
            pStmtDLT.setInt(1, userID);
            int result = pStmtDLT.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	//change all values in cart Table if user changed it
	public void ConfirmedPurchase(String userID, int FetchItemID, int FetchSizeID, int FetchColorID, int FetchOrders) {
        boolean success = false; 
        
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
        	
        	int UserNum = getUserIDsql(userID);
        	String sqlUpdateOrders = "UPDATE carts SET orders = ? WHERE UserID = ? AND itemID = ? AND sizeID = ? AND colorID = ?";
        	System.out.println("doPost TY cartDAO > updating orders("+ FetchOrders +") of userid ("+ UserNum +"), of item ID ("+ FetchItemID +")");
            try (PreparedStatement pstmtUpdateOrders = conn.prepareStatement(sqlUpdateOrders)) {
                pstmtUpdateOrders.setInt(1, FetchOrders);
                pstmtUpdateOrders.setInt(2, UserNum);  
                pstmtUpdateOrders.setInt(3, FetchItemID);
                pstmtUpdateOrders.setInt(4, FetchSizeID);
                pstmtUpdateOrders.setInt(5, FetchColorID);
                int rowsUpdated = pstmtUpdateOrders.executeUpdate();
                if (rowsUpdated > 0) {
                    success = true;
                    System.out.println("Orders updated successfully!");
                } else {
                    System.out.println("No rows updated. User not found or no matching criteria.");
                }
            }
            //finisshed cartorderUpdate
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  // last check of item orders end.
	

	public int getUserIDsql(String userID) { 
		int UserNum=0;
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

		 try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
			    String sqlUserNum= "SELECT id FROM users WHERE userID= ?";
		        
		        	 try (PreparedStatement pstmtUserNum = conn.prepareStatement(sqlUserNum)){
		                 pstmtUserNum.setString(1, userID);
		                 try (ResultSet rsUserNum = pstmtUserNum.executeQuery()) {
		                     if ( rsUserNum.next()) {
		                         userID =  rsUserNum.getString("id");
		                     }
		                 }
		             }
	        	UserNum= Integer.parseInt(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return UserNum;
    }
	
	
	
	
	
	
	public boolean IntoCartDAO(IntoCartBO obj) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
        		int UserNum= 0;
        		String sqlUserNum= "SELECT id FROM users WHERE userID= ?";
	        
	        	 try (PreparedStatement pstmtUserNum = conn.prepareStatement(sqlUserNum)){
	                 pstmtUserNum.setString(1, obj.getUserID());
	                 try (ResultSet rsUserNum = pstmtUserNum.executeQuery()) {
	                     if ( rsUserNum.next()) {
	                         UserNum =  rsUserNum.getInt("id");
	                     }
	                 }
	             }
	        	System.out.println(UserNum);
	        	
	        	int ColrNum= 0;
	        	String sqlColrNum= "SELECT id FROM colors WHERE name= ?";
	        	 try (PreparedStatement pstmtColrNum = conn.prepareStatement(sqlColrNum)){
	        		 pstmtColrNum.setString(1, obj.getColor());
	                 try (ResultSet rsColrNum = pstmtColrNum.executeQuery()) {
	                     if ( rsColrNum.next()) {
	                         ColrNum =  rsColrNum.getInt("id");
	                     }
	                 }
	             }
	        	System.out.println(ColrNum);
	        	
	        	int SizeNum= 0;
	        	String sqlSizeNum= "SELECT id FROM sizes WHERE name= ?";
	        	 try (PreparedStatement pstmtSizeNum = conn.prepareStatement(sqlSizeNum)){
	        		 pstmtSizeNum.setString(1, obj.getSize());
	                 try (ResultSet rsSizeNum = pstmtSizeNum.executeQuery()) {
	                     if ( rsSizeNum.next()) {
	                         SizeNum =  rsSizeNum.getInt("id");
	                     }
	                 }
	             }
	        	 System.out.println(SizeNum);
	        	 
        		String selectQuery = "SELECT orders FROM carts WHERE userID = ? AND itemID = ? AND sizeID = ? AND colorID = ?";
	            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
	            selectStmt.setInt(1, UserNum);
	            selectStmt.setInt(2, obj.getItemID());
	            selectStmt.setInt(3, SizeNum);
	            selectStmt.setInt(4, ColrNum);
	
	            ResultSet rs = selectStmt.executeQuery();
	            boolean rowUpdated = false;

	            while (rs.next()) {
	                // For each matching row, update the orders column
	                int existingOrders = rs.getInt("orders");
	                int newOrders = existingOrders + obj.getOrders();

	                String updateQuery = "UPDATE carts SET orders = ? WHERE userID = ? AND itemID = ? AND sizeID = ? AND colorID = ?";
	                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
	                updateStmt.setInt(1, newOrders);
	                updateStmt.setInt(2, UserNum);
	                updateStmt.setInt(3, obj.getItemID());
	                updateStmt.setInt(4, SizeNum);
	                updateStmt.setInt(5, ColrNum);
		            
	                int rowsAffected = updateStmt.executeUpdate();
	                if (rowsAffected > 0) {
	                    rowUpdated = true;
	                }
	            }

	            if (!rowUpdated) {
		        	String sql ="INSERT INTO `carts`(`userID`, `itemID`, `colorID`, `sizeID`, `orders`) SELECT u.ID AS userID, ? AS itemID, c.id AS colorID, s.id AS sizeID, ? AS orders FROM users AS u JOIN colors AS c ON c.name = ? JOIN sizes AS s ON s.name = ? WHERE u.userID = ?";
		            PreparedStatement pStmt = conn.prepareStatement(sql);
		            pStmt.setInt(1, obj.getItemID());
		            pStmt.setInt(2, obj.getOrders());
		            pStmt.setString(3, obj.getColor());
		            pStmt.setString(4, obj.getSize());
		            pStmt.setString(5, obj.getUserID());
					int result = pStmt.executeUpdate();
					if (result != 1) {
						return false;
					}
	            }
        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }

}
