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




    private static void showTransactionHistory(User curUser, Scanner scanner) {
        int theAccount;
        // ge the account whose transaction you want to see
        do {
            System.out.printf("Enter the number (1-%d) of the account \n" +
                    "whose transaction you want to see: ", curUser.numAccont() );
            theAccount = scanner.nextInt()-1;
            if (theAccount < 0 || theAccount >= curUser.numAccont()){
                System.out.println("Invalid account !! please try again");
            }

        }while (theAccount < 0 || theAccount >= curUser.numAccont());
        // print the transaction history
        curUser.printAccountTransactionHistory(curUser.numAccont());
    }

    /**
     * Transferring funds
     * @param curUser
     * @param scanner
     */
    private static void transferFunds(User curUser, Scanner scanner) {
        //inits
        int fromAccount;
        int toAccount;
        double amount;
        double actualBalance;

        // get the amount to transfer from the account
        do {


            System.out.println("Enter the number (1-%d) of the account\n" +
                    "your transferring money from");
            fromAccount = scanner.nextInt() - 1;
            if (fromAccount < 0 || fromAccount >= curUser.numAccont()){
                System.out.println("Invalid account !! Please valid account!");

            }
        }while (fromAccount < 0 || fromAccount >= curUser.numAccont());
        actualBalance = curUser.getActualBalance(fromAccount);

        do {


            System.out.println("Enter the number (1-%d) of the account\n" +
                    "your transferring money to");
            toAccount = scanner.nextInt() - 1;
            if (toAccount < 0 || toAccount >= curUser.numAccont()){
                System.out.println("Invalid account !! Please valid account!");

            }
        }while (toAccount < 0 || toAccount >= curUser.numAccont());

         // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer: (max %$ . 02f):$",actualBalance);
            amount = scanner.nextInt() - 1;
            if (amount < 0){
                System.out.println("Amount should be greater than zero");
            } else if (amount > actualBalance) {
                System.out.printf("You have insufficient fund on your account\n" +
                        "please check your balance first $%.02f.\n",actualBalance);

            }


        }while (amount < 0 || amount > actualBalance);
        // you can now do the transfer
        curUser.addAccountTransaction(fromAccount, -1*amount,String.format("Transfer to account",
                curUser.getAcctUUID(toAccount)));
        curUser.addAccountTransaction(toAccount, amount,String.format("Transfer to account",
                curUser.getAcctUUID(fromAccount)));


    }
    private static void withdrewFunds(User curUser, Scanner scanner) {
        // init
        int fromAccount;
        double amount;
        double actualBalance;
        String memo;

        // get the amount to transfer from the account
        do {


            System.out.println("Enter the number (1-%d) of the account\n" +
                    "your transferring money from");
            fromAccount = scanner.nextInt() - 1;
            if (fromAccount < 0 || fromAccount >= curUser.numAccont()){
                System.out.println("Invalid account !! Please valid account!");

            }
        }while (fromAccount < 0 || fromAccount >= curUser.numAccont());
        actualBalance = curUser.getActualBalance(fromAccount);
        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer: (max %$ . 02f):$",actualBalance);
            amount = scanner.nextInt() - 1;
            if (amount < 0){
                System.out.println("Amount should be greater than zero");
            } else if (amount > actualBalance) {
                System.out.printf("You have insufficient fund on your account\n" +
                        "please check your balance first $%.02f.\n",actualBalance);

            }


        }while (amount < 0 || amount > actualBalance);
        // global up rest of the previous input
        scanner.nextLine();
        // get memo
        System.out.println("Enter a memo:");
        memo = scanner.nextLine();
        // get withdraw
        curUser.addAccountTransaction(fromAccount,-1*amount,memo);



    }
    private static void depositFunds(User curUser, Scanner scanner) {
        int toAccount;
        double amount;
        double actualBalance;
        String memo;

        // get the amount to transfer from the account
        do {


            System.out.println("Enter the number (1-%d) of the account\n" +
                    "your transferring money from");
            toAccount = scanner.nextInt() - 1;
            if (toAccount < 0 || toAccount >= curUser.numAccont()){
                System.out.println("Invalid account !! Please valid account!");

            }
        }while (toAccount < 0 || toAccount >= curUser.numAccont());
        actualBalance = curUser.getActualBalance(toAccount);
        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer: (max %$ . 02f):$",actualBalance);
            amount = scanner.nextInt() - 1;
            if (amount < 0){
                System.out.println("Amount should be greater than zero");
            } else if (amount > actualBalance) {
                System.out.printf("You have insufficient fund on your account\n" +
                        "please check your balance first $%.02f.\n",actualBalance);

            }


        }while (amount < 0 || amount > actualBalance);
        // global up rest of the previous input
        scanner.nextLine();
        // get memo
        System.out.println("Enter a memo:");
        memo = scanner.nextLine();
        // get withdraw
        curUser.addAccountTransaction(toAccount,amount,memo);


    }
}