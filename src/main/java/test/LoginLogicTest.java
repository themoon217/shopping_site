package test;

import model.Login;
import model.LoginLogic;

public class LoginLogicTest {
    public static void main(String[] args) throws Exception {
        testExcuteOK();
        testExcuteNG();
    }
    public static void testExcuteOK() throws Exception {
        Login login = new Login("aaaa", "1111");
        LoginLogic bo = new LoginLogic();
        boolean result = bo.execute(login);
        if (result) {
            System.out.println("OK1");
        } else {
            System.out.println("NG1");
        }
    }
    public static void testExcuteNG() throws Exception {
        Login login = new Login("minato", "12345");
        LoginLogic bo = new LoginLogic();
        boolean result = bo.execute(login);
        if (!result) {
            System.out.println("OK2");
        } else {
            System.out.println("NG2");
        }
    }
}