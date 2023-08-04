package banking;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHah[];
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;


         // Stores the pin MD5 hash rather than the original values for the security reasons

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHah = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.printf("NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        this.uuid = theBank.getNewUserUUID();
        // create empty list of account
        this.accounts = new ArrayList<Account>();
        // print the log message
        System.out.printf("New user %s,%s wit the ID %s created \n", firstName, lastName, this.uuid);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUUID() {
        return this.uuid;
    }


    public boolean validatePin(String pin) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(messageDigest.digest(pin.getBytes()),
                    this.pinHah);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid userID or pin ");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this. firstName;
    }

    /**
     *  Accounts summary
     */
    public void printAccountSummary() {
        System.out.printf("\n\n %s's accounts summary",this.firstName);
        for (int account = 0; account < this.accounts.size(); account++ ){
            System.out.printf("%d %s\n",this.accounts.get(account).getSummaryLine());
        }
    }

    public int numAccont() {
        return this.accounts.size();
    }

    public void printAccountTransactionHistory(int accountIndex) {
         this.accounts.get(accountIndex).printTransactionHistory();
    }

    public double getActualBalance(int accountIndex) {
        return  this.accounts.get(accountIndex).getBalance();

    }

    public void addAccountTransaction(int fromAccount, double v, String transfer_to_account) {
    }
}

