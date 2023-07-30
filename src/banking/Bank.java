package banking;

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

    public String getNewUserUUID() {
        // init
        String uuid;
        Random random = new Random();
        int leg = 6;
        boolean nonUnique;

        /**
         *    Continue looping until the condition meets the uuid
          */

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
     * @return
     */

    public String getNewAccountUUID() {
        // init
        String uuid;
        Random random = new Random();
        int leg = 6;
        boolean nonUnique;

        /**
         * Continue looping until the condition is true
          */


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
     * Add accounts to the bank
     * @param account
     */

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * A
     * @param firstName
     * @param lastName
     * @param pin
     * @return
     */
    public User addUser(String firstName, String lastName, String pin){
        User newUser = new User(firstName,lastName,pin,this);
        return  this.addUser(firstName,lastName,pin);
    }
}
