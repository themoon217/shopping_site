package model;

import dao.UserDAO;

public class LoginLogic {
    public boolean execute(Login login) {
        UserDAO dao = new UserDAO();
        Account account = dao.findByLogin(login);
        return account != null;
    }
}