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
     * @param
     * @param scanner
     */

    private static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        // init the credential
        String userID;
        String pin;
        User authUser;
        do {
            System.out.printf("\n\n Hi welcome %s\n\n",theBank.getName());
            System.out.print("Enter user ID:");
            userID = scanner.nextLine();
            System.out.print("Enter pin:");
            pin = scanner.nextLine();

            // Authenticate user to log in theBank
            authUser = theBank.userLogin(userID,pin);
            if (authUser == null){
                System.out.println("Incorrect userID and pin please try  again");
            }

        }while (authUser == null);// This tells us to continue looping until successful login
        return authUser;
    }
    private static void printUserMenu(User curUser, Scanner scanner) {
        curUser.printAccountSummary();

        int choice;

        do {
            System.out.printf(" Welcome take your choice ",
                    curUser.getFirstName());
            System.out.println(" 1) Show Transaction history");
            System.out.println(" 2) Withdraw");
            System.out.println(" 3) Transfer ");
            System.out.println(" 4) Deposit");
            System.out.println(" 5) Exit");
            System.out.println("..............................................");
            System.out.println("..............................................");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            if (choice <1 || choice >5){
                System.out.println("invalid choice please take the right choice");
            }

        }while (choice < 1 || choice > 5);

        // process the choices now
        switch (choice){
            case 1:
                Main.showTransactionHistory(curUser,scanner);
                break;
            case 2:
                Main.withdrewFunds(curUser,scanner);
                break;
            case 3:
                Main.depositFunds(curUser,scanner);
                break;
            case 4:
                Main.transferFunds(curUser,scanner);
                break;
        }
        // Redisplay this menu when the user wants quite
         if(choice != 5){
             Main.printUserMenu(curUser,scanner);
         }
    }
}