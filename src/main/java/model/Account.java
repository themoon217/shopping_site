package model;

public class Account {
    private String userID;
    private String pass;

    public Account(String userID, String pass) {
        this.userID = userID;
        this.pass = pass;
    }

    public String getUserID() {
        return userID;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}