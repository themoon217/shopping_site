package model;

public class CheckStockData {
	private String itemName;
    private String imgPath;
    private String color;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int itemID;
    private int colorID;
    public CheckStockData (String itemName, String imgPath, String color, int s, int m, int l, int xl, int itemID, int colorID) {
        this.itemName = itemName;
        this.imgPath = imgPath;
        this.color = color;
        this.s = s;
        this.m = m;
        this.l = l;
        this.xl = xl;
        this.itemID = itemID;
        this.colorID = colorID;
    }

    public CheckStockData () {}

    public String getItemName() {
        return itemName;
    }
    public String getImgPath() {
        return imgPath;
    }
    public String getColor() {
        return color;
    }
    public int getS() {
        return s;
    }
    public int getM() {
        return m;
    }
    public int getL() {
        return l;
    }
    public int getXl() {
        return xl;
    }
    public int getItemID() {
        return itemID;
    }
    public int getColorID() {
        return colorID;
    }
}
