package model;

import java.util.List;

import dao.CartDAO;

public class GetCartLogic {	
	
	public List<Cart> execute(String account){
		CartDAO dao = new CartDAO();
		List<Cart> cartList = dao.findAll(account);
		return cartList;
	}
}
