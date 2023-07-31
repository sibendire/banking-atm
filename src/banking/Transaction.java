package banking;

import java.util.Date;

public class Transaction {
    private String memo;
    private double amount;
    private Date timeOfTransaction;
    private Account inAccount;

    /**
     * constructor
     *
     * @param amount
     * @param inAccount
     */
    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timeOfTransaction = new Date();
        this.memo = "";


    }

    /**
     * Constructor
     *
     * @param amount
     * @param inAccount
     * @param memo
     */
    public Transaction(double amount, Account inAccount, String memo) {
        // Call the two arg constructor
        this(amount, inAccount);
        this.memo = memo;

    }
}
