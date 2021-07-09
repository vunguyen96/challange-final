package vn.axonactive.model;

public class Wallet {
    private String id;
    private String userName;
    private int balance;

    public Wallet() {
    }

    public Wallet(String id, String userName, int balance) {
        this.id = id;
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}