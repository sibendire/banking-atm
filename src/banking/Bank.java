package banking;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    /**
     * Generate the uuid for the user
     *
     * @return
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }

    public String getNewUserUUID() {
        // init
        String uuid;
        Random random = new Random();
        int leg = 8;
        boolean nonUnique;

        // Continue looping until the condition meets the uuid


        do {
            // Generate the uuid

            uuid = "";
            nonUnique = false;
            for (int c = 0; c < leg; c++) {
                uuid += ((Integer) random.nextInt(10)).toString();

                //Check to  make sure its unique

                for (User user : this.users) {
                    if (uuid.compareTo(user.getUUID()) == 0) {
                        nonUnique = true;
                        break;
                    }
                }

            }

        } while (nonUnique);
        return uuid;
    }

    /**
     * Generate the uuid for an account
     *
     * @return
     */

    public String getNewAccountUUID() {
        // init
        String uuid;
        Random random = new Random();
        int leg = 10;
        boolean nonUnique;

        // Continue looping until the condition is true


        do {
            // Generate the uuid

            uuid = "";
            nonUnique = false;
            for (int c = 0; c < leg; c++) {
                uuid += ((Integer) random.nextInt(10)).toString();

                //Check to  make sure its unique

                for (Account account : this.accounts) {
                    if (uuid.compareTo(account.getUUID()) == 0) {
                        nonUnique = true;
                        break;
                    }
                }

            }

        } while (nonUnique);
        return uuid;
    }

    /**
     * Create a new user in the bank
     *
     * @param firstName
     * @param lastName
     * @param pin
     * @return
     */
    public User addUser(String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // Create newAccount for the user in the Bank

        Account newAccount = new Account("saving", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);
        return newUser;
    }

    /**
     * Get user associated with the user ID and pin if valid to login account
     *
     * @param userID
     * @param pin
     * @return
     */
    public User userLogin(String userID, String pin) {
        // First search user from the list of other user

        for (User user : this.users) {
            if (user.getUUID().compareTo(userID) == 0 && user.validatePin(pin)) {
                return user;
            }
        }
        return null;
    }


    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    public String getName() {
        return this.name;
    }
}


