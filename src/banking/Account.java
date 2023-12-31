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

         // create the list of transactions made in a particular account

        this.transactions = new ArrayList<Transaction>();

    }

    /**
     * Create a method get uuid for the account
     * @return
     */
    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine() {
        // first get the account balance
        double balance = this.getBalance();

        // Format the summary line depends on whether the balance is negative
        if (balance >= 0){
            return String.format("%s: $%.02f:%s", this.uuid,balance,this.name);
        }else {
            return String.format("%s:$(%.02f):%s", this.uuid,balance,this.name);
        }
    }

    double getBalance() {
        double balance = 0;
        for (Transaction transaction : this.transactions){
            balance += transaction.getAmount();
        }
        return balance;
    }

    public void printTransactionHistory() {
        System.out.printf("\n Transaction for the Account" , this.uuid);
        for (int transaction = this.transactions.size()-1; transaction >= 0; transaction--){
            System.out.printf(this.transactions.get(transaction).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {
        // Create a new transaction object and dd to the list
        Transaction newTransaction = new Transaction(amount, this, memo);
        this.transactions.add(newTransaction);
    }
}
