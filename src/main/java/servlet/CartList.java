package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.UserDAO;
import model.Account;
import model.Cart;
import model.GetCartLogic;

@WebServlet("/CartList")
public class CartList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("doget from CartList started.");

    	HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String userID = account.getUserID();
		System.out.println("cartLIST userIDcheck:"+userID);
		
    	GetCartLogic getCartLogic =	new GetCartLogic();
    	System.out.println("doget cartlogic instance created.");
		List<Cart> cartList = getCartLogic.execute(userID);
		System.out.println("doget cartgetlogic executed..");
		
        System.out.println("cart info from sql acquired.");
        request.setAttribute("cartList", cartList);
        System.out.println("cart info from sql saved.");
        System.out.println("cart info - cartList var:");
        System.out.println(cartList);     
		UserDAO dao = new UserDAO();
        int point = dao.getPoint(userID);
        request.setAttribute("point", point);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartList post Start.");
		
    	HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String userID = account.getUserID();
		System.out.println(">>>>>>>>>>> ERROR CHECK delete <<<<<<<<<<<<<<<");
		System.out.println("cartLIST >Post userIDcheck:"+userID);
    	request.setCharacterEncoding("UTF-8");
    	int DELETEitemID= Integer.parseInt(request.getParameter("iDEL"));
    	System.out.println("iDEL" + DELETEitemID);
    	int DELETEsizeID= Integer.parseInt(request.getParameter("siDEL"));
    	System.out.println("siDEL" + DELETEsizeID);
    	int DELETEcolorID= Integer.parseInt(request.getParameter("ciDEL"));
    	System.out.println("ciDEL" + DELETEcolorID);
		CartDAO dao = new CartDAO();
		if (dao.deleteCartItem(DELETEitemID,DELETEsizeID,DELETEcolorID)) {
				System.out.println("CartDAO returned true");
		} else {
		    	System.out.println("CartDAO returned false");
		}
		doGet(request, response);
	}
}		
