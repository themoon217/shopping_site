package model;

public class RankingItem {
    private Item item; // 元の Item オブジェクト
    private int totalSales; // 売上枚数

    // コンストラクタ
    public RankingItem(Item item, int totalSales) {
        this.item = item;
        this.totalSales = totalSales;
    }

    // 各フィールドの getter/setter メソッド
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
}