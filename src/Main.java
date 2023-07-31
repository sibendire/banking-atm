import banking.Account;
import banking.Bank;
import banking.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to to ATM!");

        Scanner scanner = new Scanner(System.in);

        Bank theBank = new Bank("Rubaga");
        // Add user to the Bank
        User user = theBank.addUser("Sibendire","Joshua","1234");
        // Add a checking account for the user in the bank and if it doesn't exist
        // please add
        Account newAccount = new Account("Saving account",user,theBank);
        user.addAccount(newAccount);
        theBank.addAccount(newAccount);

        // loop through
        User curUser;
        while (true){
            // prompt the user to login until is successful
            curUser = Main.mainMenuPrompt(theBank,scanner);
            // Stay in the menu until its quit
            Main.printUserMenu(curUser,scanner);

        }


    }

    /**
     * provide the menu that prompt the user to enter credentials
     * @param curUser
     * @param scanner
     */

    private static void printUserMenu(User curUser, Scanner scanner) {
        // init the credential
        String userID;
        String pin;
        User authUser;
    }

    private static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        return
    }
}