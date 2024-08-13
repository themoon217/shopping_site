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
import dao.Transaction;
import model.Account;
import model.Cart;
import model.GetCartLogic;

@WebServlet("/ThankYou")
public class ThankYou extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.sendRedirect("index.jsp"); // Redirect to index.jsp or any other page
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ThankYou.servlet post Start.");
		
    	HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String userID = account.getUserID();
		System.out.println("TY >Post userIDcheck: "+ userID);
    	request.setCharacterEncoding("UTF-8");
    
    	// get all parameter values:
    	String[] orders= request.getParameterValues("oDEL");
    	String[] itemIDs= request.getParameterValues("iDEL");
    	String[] sizeIDs= request.getParameterValues("siDEL");
    	String[] colorIDs= request.getParameterValues("ciDEL");
    	    	
    	String[] iNames= request.getParameterValues("INDEL");
    	
    	String[] prices=request.getParameterValues("pDEL");
    	String[] imgPaths= request.getParameterValues("iPDEL");
    	
    	String[] colrNames=request.getParameterValues("cNDEL");
    	String[] sizeNames=request.getParameterValues("sNDEL");
    	
    	System.out.println("TY >Post ordercheck : " + orders);
    	System.out.println("TY >Post inamecheck : " + iNames);
    	System.out.println("TY >Post iID, size, color : " + itemIDs + " , " + sizeIDs + " , " + colorIDs);
    	

    	// update order numbers inside the cart on mysql table
    	CartDAO dao = new CartDAO();
    	
    	 int numberOfItems = itemIDs.length;
         for (int i = 0; i < numberOfItems; i++) {
             int ItemID = Integer.parseInt(itemIDs[i]);
             int SizeID = Integer.parseInt(sizeIDs[i]);
             int ColorID = Integer.parseInt(colorIDs[i]);
             int Orders = Integer.parseInt(orders[i]);
             
             dao.ConfirmedPurchase(userID,ItemID,SizeID,ColorID,Orders);
		 }
            
        System.out.println("TY DAO > Confirmed purchase returned true");		
		//get updated cart from msql table
        GetCartLogic getCartLogic =	new GetCartLogic();
    	System.out.println("doPost TY cartDAO logic instance created.");
		List<Cart> cartList = getCartLogic.execute(userID);
		// start transaction:
		
		Transaction totalp = new Transaction();
		totalp.TransactionDO(cartList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("thank.jsp");
        dispatcher.forward(request, response);
	}
}