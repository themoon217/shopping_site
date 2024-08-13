package servlet;

import dao.CartDAO;
import model.IntoCartBO;

public class IntoCartMethod {
    public boolean execute(IntoCartBO intoCart) {
        CartDAO dao = new CartDAO();
        boolean done = dao.IntoCartDAO(intoCart);
        return done;
    }
}