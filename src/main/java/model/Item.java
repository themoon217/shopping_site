package model;

public class Item {
    private int id;
    private String name;
    private String imgPath;
    private int price;
    private String colors; // 色情報を保持

    public Item(int id, String name, String imgPath, int price, String colors) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
        this.price = price;
        this.colors = colors;
    }

    public Item() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// 各フィールドの getter/setter メソッド
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }
}