package model;

public class AddSubImgData {
	private int itemID;
    private String subImgPath;
    public AddSubImgData (int itemID, String subImgPath) {
        this.itemID = itemID;
        this.subImgPath = subImgPath;
    }
    public int getItemID() {
        return itemID;
    }
    public String getSubImgPath() {
        return subImgPath;
    }
}
