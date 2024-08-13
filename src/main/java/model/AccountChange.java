package model;

public class AccountChange {
    private String userID;
    private String pass;
    private String name;

    public AccountChange(String userID, String pass, String name) {
        this.userID = userID;
        this.pass = pass;
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }
}