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

        /**
         * Stores the pin MD5 hash rather than the original values for the security reasons
         *
         */
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
}
