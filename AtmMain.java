import java.util.*;
class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +$" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdrawal: -$" + amount);
        return true;
    }

    public void changePin(String newPin) {
        pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

// Class representing the ATM machine
public class ATMMain {
    private BankAccount currentAccount;
    private Scanner scanner;

    public ATMMain() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        authenticate();
    }

    private void authenticate() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        BankAccount account = findAccount(accountNumber);

        if (account != null && account.validatePin(pin)) {
            currentAccount = account;
            showOptions();
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    private BankAccount findAccount(String accountNumber) {
     
        if (accountNumber.equals("123456")) {
            return new BankAccount("123456", "1234", 1000); 
        }
        return null;
    }

    private void showOptions() {
        int choice;
        do {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawCash();
                    break;
                case 3:
                    depositCash();
                    break;
                case 4:
                    changePIN();
                    break;
                case 5:
                    printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + currentAccount.getBalance());
    }

    private void withdrawCash() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (currentAccount.withdraw(amount)) {
            System.out.println("Please take your cash.");
        }
    }

    private void depositCash() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        currentAccount.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private void changePIN() {
        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine();
        currentAccount.changePin(newPin);
    }

    private void printTransactionHistory() {
        currentAccount.printTransactionHistory();
    }

    public static void main(String[] args) {
        ATMMain atm = new ATMMain();
        atm.start();
    }
}
