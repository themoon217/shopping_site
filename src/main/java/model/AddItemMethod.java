package model;

import dao.ItemsDAO;

public class AddItemMethod {
    public boolean execute(AddItemData addItem) {
        ItemsDAO dao = new ItemsDAO();
        System.out.println("okokM");

        boolean done = dao.AddItem(addItem);
		return done;
    }
}
