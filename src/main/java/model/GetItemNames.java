package model;

import java.util.ArrayList;

import dao.ItemsDAO;

public class GetItemNames {
    public ArrayList<Item> itemList() {
        ItemsDAO dao = new ItemsDAO();
        ArrayList<Item> itemList = dao.getAllItemDAO();
        return itemList;
    }
}
