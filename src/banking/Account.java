package banking;

import java.util.ArrayList;

public class Account {
    private String name;
    private double amount;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    /**
     *  Create account constructor
     * @param name
     * @param holder
     * @param theBank
     */
    public Account(String name, User holder, Bank theBank) {

         // set the account name and holder

        this.name = name;
        this.holder = holder;

         // create new account uuid

        this.uuid = theBank.getNewAccountUUID();

         /// create the list of transactions

        this.transactions = new ArrayList<Transaction>();

    }

    public String getUUID() {
        return this.uuid;
    }
}
