package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AddItemData;
import model.CategoryData;
import model.ColorData;
import model.Item;
import model.ItemData;
import model.Recommendation;
import model.Subimg;

public class ItemsDAO {
	public List<Recommendation> findRecom() {
        List<Recommendation> recomList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT i.id as itemID, name, price, imgPath FROM `recommendations` AS r JOIN `items` AS i WHERE i.id = r.itemID";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                Recommendation recom = new Recommendation(itemID, name, price, imgPath);
                recomList.add(recom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recomList;
    }
	public List<Recommendation> findNotRecom() {
        List<Recommendation> recomList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql ="SELECT i.id as itemID, i.name, i.price, i.imgPath\r\n" + //
                        "FROM items AS i\r\n" + //
                        "LEFT JOIN recommendations AS r ON i.id = r.itemID\r\n" + //
                        "WHERE r.itemID IS NULL;";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                Recommendation recom = new Recommendation(itemID, name, price, imgPath);
                recomList.add(recom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recomList;
    }

    public ItemData getItemDataDAO(int itemID) {
        ItemData itemData = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT name, imgPath, price, AVG(r.evaluation) AS evaluat, COUNT(r.itemID) AS comments FROM items AS i LEFT JOIN reviews AS r ON i.id = r.itemID WHERE i.id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemID);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                double evaluat = rs.getDouble("evaluat");
                int comments = rs.getInt("comments");
                itemData = new ItemData(name, price, imgPath, evaluat, comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemData;
    }

    public ColorData getColorList(int itemID) {
        ColorData colorData = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT c.name AS name FROM items_colors AS ic JOIN colors AS c ON ic.colorID = c.id WHERE ? = ic.itemID";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemID);
            ResultSet rs = pStmt.executeQuery();
            List<String> colorList = new ArrayList<String>();
            while (rs.next()) {
                String colorName = rs.getString("name");
                colorList.add(colorName);
            }
            colorData = new ColorData(colorList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colorData;
    }

    public Subimg getImgList(int itemID) {
        Subimg subimg = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT `imgPath` FROM `subimg` WHERE `itemID` = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemID);
            ResultSet rs = pStmt.executeQuery();
            List<String> imgList = new ArrayList<String>();
            while (rs.next()) {
                String imgStr = rs.getString("imgPath");
                imgList.add(imgStr);
            }
            subimg = new Subimg(imgList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subimg;
    }

    public CategoryData getCategoryList(int itemID) {
        CategoryData categoryData = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT c.name AS category FROM `items_categories` AS ic JOIN `categories` AS c ON ic.categoryID = c.id WHERE ic.itemID = ?;";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, itemID);
            ResultSet rs = pStmt.executeQuery();
            List<String> categoryList = new ArrayList<String>();
            while (rs.next()) {
                String categoryStr = rs.getString("category");
                categoryList.add(categoryStr);
            }
            categoryData = new CategoryData(categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryData;
    }

    public boolean AddItem(AddItemData addItem) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            conn.setAutoCommit(false); // トランザクション開始
    
            // アイテムの基本情報を挿入
            String sql = "INSERT INTO items(name, price, imgPath, content, nothing) VALUES (?, ?, ?, '', 0)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, addItem.getName());
                pStmt.setInt(2, addItem.getPrice());
                pStmt.setString(3, addItem.getImgPath());
                int result = pStmt.executeUpdate();
                if (result != 1) {
                    throw new IllegalStateException("アイテムの登録に失敗しました");
                }
            }
    
            // 追加したアイテムのIDを取得
            sql = "SELECT id FROM items WHERE name = ?";
            int itemID = 0;
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, addItem.getName());
                ResultSet rs = pStmt.executeQuery();
                if (rs.next()) {
                    itemID = rs.getInt("id");
                } else {
                    throw new IllegalStateException("アイテムのIDの取得に失敗しました");
                }
            }
    
            // アイテムのサイズ情報を挿入
            sql = "INSERT INTO items_sizes(itemID, sizeID) VALUES (?, ?), (?, ?), (?, ?), (?, ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                for (int i = 1; i <= 4; i++) {
                    pStmt.setInt(i * 2 - 1, itemID);
                    pStmt.setInt(i * 2, i);
                }
                int result = pStmt.executeUpdate();
                if (result != 4) {
                    throw new IllegalStateException("アイテムのサイズ情報の登録に失敗しました");
                }
            }
    
            // アイテムのカラー情報を挿入
            sql = "INSERT INTO items_colors(itemID, colorID) SELECT ?, id FROM colors WHERE name IN (?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                for (String color : addItem.getColorList()) {
                    pStmt.setInt(1, itemID);
                    pStmt.setString(2, color);
                    int result = pStmt.executeUpdate();
                    if (result != 1) {
                        throw new IllegalStateException("アイテムのカラー情報の登録に失敗しました");
                    }
                }
            }
    
            // アイテムのカテゴリ情報を挿入
            sql = "INSERT INTO items_categories(itemID, categoryID) SELECT ?, id FROM categories WHERE name IN (?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                for (String category : addItem.getCategories()) {
                    pStmt.setInt(1, itemID);
                    pStmt.setString(2, category);
                    int result = pStmt.executeUpdate();
                    if (result != 1) {
                        throw new IllegalStateException("アイテムのカテゴリ情報の登録に失敗しました");
                    }
                }
            }

            // 在庫情報を挿入
            sql = "INSERT INTO stocks(itemID, colorID, sizeID, stock) SELECT ?, ic.colorID, isi.sizeID, 50 FROM items i JOIN items_colors ic ON i.id = ic.itemID JOIN items_sizes isi ON i.id = isi.itemID WHERE i.id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, itemID);
                pStmt.setInt(2, itemID);
                int result = pStmt.executeUpdate();
                if (result < 1) {
                    throw new IllegalStateException("アイテムの在庫情報の登録に失敗しました");
                }
            }
    
            conn.commit(); // トランザクションのコミット
        } catch (SQLException e) {
            throw new IllegalStateException("アイテムの登録中にエラーが発生しました", e);
        }
    
        return true;
    }

    public ArrayList<Item> getAllItemDAO() {
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT `id`, `name`, `imgPath`, `price` FROM `items`";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String imgPath = rs.getString("imgPath");
                int price = rs.getInt("price");
                Item item = new Item(id, name, imgPath, price, null);
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public int getItemID(String name) {
        int itemID = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "SELECT `id` FROM `items` WHERE name = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                itemID = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(itemID);
        }
        return itemID;
    }

    public boolean deleteItem(int itemId) {
        boolean success = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "DELETE FROM items WHERE id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setInt(1, itemId);
                int result = pStmt.executeUpdate();
                if (result > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void addRecommendation(String itemName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql ="INSERT INTO `recommendations`(`itemID`)\r\n" + //
                        "SELECT i.id FROM items AS i WHERE i.name = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, itemName);
                int result = pStmt.executeUpdate();
                if (result == 0) {
                    System.out.println("おすすめ追加に失敗しました。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRecommendation(String itemName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql ="DELETE FROM `recommendations` WHERE itemID = (SELECT i.id FROM items AS i WHERE i.name = ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, itemName);
                int result = pStmt.executeUpdate();
                if (result == 0) {
                    System.out.println("おすすめ削除に失敗しました。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean updateItemPrice(int itemID, double newPrice) {
        boolean success = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moonwith?charaterEncoding=UTF-8&serverTimezone=JST", "root", "")) {
            String sql = "UPDATE items SET price = ? WHERE id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setDouble(1, newPrice);
                pStmt.setInt(2, itemID);
                int result = pStmt.executeUpdate();
                if (result > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
