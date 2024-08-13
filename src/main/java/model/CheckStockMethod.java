package model;

import java.util.ArrayList;

import dao.StockDAO;

public class CheckStockMethod {
	public ArrayList<CheckStockData> execute() {
        StockDAO dao = new StockDAO();
        ArrayList<CheckStockData> stockDatas = dao.getAllItemStockDAO();
        return stockDatas;
    }
	public CheckStockData executeStock(int itemID, int colorID) {
        StockDAO dao = new StockDAO();
        CheckStockData stockData = dao.getItemStockDAO(itemID, colorID);
        return stockData;
    }
}
