package model;

public class Recommendation {
	private int itemID;
    private String name;
    private int price;
    private String imgPath;
    public Recommendation (int itemID, String name, int price, String imgPath) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
    }
    public int getItemID() {
        return itemID;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getImgPath() {
        return imgPath;
    }
}
