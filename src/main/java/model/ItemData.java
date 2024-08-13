package model;

public class ItemData {
    private String name;
    private int price;
    private String imgPath;
    private double evaluat;
    private int comments;

    public ItemData(String name, int price, String imgPath, double evaluat, int comments) {
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.evaluat = evaluat;
        this.comments = comments;
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

    public double getEvaluat() {
        return evaluat;
    }

    public int getComments() {
        return comments;
    }
}
