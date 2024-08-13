package test;

import dao.UserDAO;
import model.Account;
import model.Login;

public class AccountsDAOTest {
  public static void main(String[] args) {
    testFindByLoginOK(); 
    testFindByLoginNG(); 
  }
  public static void testFindByLoginOK() {
    Login login = new Login("aaaa", "1111");
    UserDAO dao = new UserDAO();
    Account result = dao.findByLogin(login);
    if (result != null && result.getUserID().equals("aaaa") && result.getPass().equals("1111")) {
      System.out.println("testFindByLoginOK:成功しました");
    } else {
      System.out.println("testFindByLoginOK:失敗しました");
    }
  }
  public static void testFindByLoginNG() {
    Login login = new Login("minato", "12345");
    UserDAO dao = new UserDAO();
    Account result = dao.findByLogin(login);
    if (result == null) {
      System.out.println("testFindByLoginNG:成功しました");
    } else {
      System.out.println("testFindByLoginNG:失敗しました");
    }
  }
}