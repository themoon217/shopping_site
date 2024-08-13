package model;

public class AddItemData {
    private String name;
    private int price;
    private String imgPath;
    private String[] colorList;
    private String[] categories;

    public AddItemData (String name, int price, String imgPath, String[] coloeList, String[] categories) {
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.colorList = coloeList;
        this.categories = categories;
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

    public String[] getColorList() {
        return colorList;
    }

    public String[] getCategories() {
        return categories;
    }
}
