/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab9.BusinessAccountManager;

import java.util.Scanner;

/**
 *
 * @author CWH
 */
interface State {

    public static State InitialState(BussinessAccount bussinesAccount) {
        return new TransactionFeeState(bussinesAccount);
    }

    public boolean withdraw(double amount);

    public void transitionState();

}

class NoTransactionFeeState implements State {

    BussinessAccount bussinesAccount;

    public NoTransactionFeeState(BussinessAccount bussinesAccount) {
        this.bussinesAccount = bussinesAccount;
    }

    @Override
    public void transitionState() {
        double accBalance = bussinesAccount.getBalance();
        if (accBalance < 0) {
            bussinesAccount.setState(new OverdrawnState(bussinesAccount));
        } else if (accBalance < BussinessAccount.MIN_BALANCE) {
            bussinesAccount.setState(new TransactionFeeState(bussinesAccount));
        }
    }

    @Override
    public boolean withdraw(double amount) {
        bussinesAccount.setBalance(bussinesAccount.getBalance() - amount);
        transitionState();
        System.out.println("An amount " + amount + " is withdrawn");
        return true;
    }
}

class TransactionFeeState implements State {

    BussinessAccount bussinesAccount;

    public TransactionFeeState(BussinessAccount bussinesAccount) {
        this.bussinesAccount = bussinesAccount;
    }

    @Override
    public void transitionState() {
        double accBalance = bussinesAccount.getBalance();
        if (accBalance >= BussinessAccount.MIN_BALANCE) {
            bussinesAccount.setState(new NoTransactionFeeState(bussinesAccount));
        } else if (accBalance < 0) {
            bussinesAccount.setState(new OverdrawnState(bussinesAccount));
        }
    }

    @Override
    public boolean withdraw(double amount) {
        bussinesAccount.setBalance(bussinesAccount.getBalance() - amount - 2);
        System.out.println("Transaction Fee ($2.0)was charged due to account status (less than minimum balance");
        transitionState();
        System.out.println("An amount " + amount + " is withdrawn");
        return true;
    }

}

class OverdrawnState implements State {

    BussinessAccount bussinesAccount;

    public OverdrawnState(BussinessAccount bussinesAccount) {
        this.bussinesAccount = bussinesAccount;
        System.out.println("Attention: Your Account is Overdrawn");
    }

    @Override
    public void transitionState() {
        double accBalance = bussinesAccount.getBalance();
        if (accBalance >= BussinessAccount.MIN_BALANCE) {
            bussinesAccount.setState(new NoTransactionFeeState(bussinesAccount));
        } else if (accBalance >= 0) {
            bussinesAccount.setState(new TransactionFeeState(bussinesAccount));
        }
    }

    @Override
    public boolean withdraw(double amount) {
        bussinesAccount.setBalance(bussinesAccount.getBalance() - amount - 5);
        System.out.println("Transaction Fee ($5.0)was charged due to account status(Overdrawn)");
        transitionState();
        System.out.println("An amount " + amount + " is withdrawn");
        return true;
    }
}

class BussinessAccount {

    public static final double MIN_BALANCE = 2000.00;
    public static final double OVERDRAW_LIMIT = -1000.00;
    public static final double TRANS_FEE_NORMAL = 2.00;
    public static final double TRANS_FEE_OVERDRAW = 5.00;
    public static final String ERR_OVERDRAW_LIMIT_EXCEED
            = "Error: Transction cannot be processed. Overdraw limit exceeded.";

    private State objState;
    private final String ACCOUNT_NUMBER;
    private double balance;

    public void setState(State newState) {
        objState = newState;
    }

    public State getState() {
        return objState;
    }

    public String getAccountNumber() {
        return ACCOUNT_NUMBER;
    }

    public boolean deposit(double amount) {
        this.setBalance(this.getBalance() + amount);
        System.out.println("An amount " + amount + " is deposited");
        objState.transitionState();
        return true;
    }

    public boolean withdraw(double amount) {
        if (this.getBalance() - amount < BussinessAccount.OVERDRAW_LIMIT) {
            System.out.println(BussinessAccount.ERR_OVERDRAW_LIMIT_EXCEED);
            return false;
        }
        return objState.withdraw(amount);
    }

    public BussinessAccount(String accountNum) {
        this.ACCOUNT_NUMBER = accountNum;
        objState = State.InitialState(this);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    @Override
    public String toString() {
        return "Account number = " + this.getAccountNumber() + ", Balance = " + this.getBalance();
    }

}

public class BusinessAccountManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choiceNum = -1;
        BussinessAccount acc1 = new BussinessAccount("1111 2222 3333 4444");

        OUTER:
        while (choiceNum != 0) {
            System.out.print("Enter 1 to display account, 2 to deposit, 3 to withdraw, 0 to exit: ");
            try {
                choiceNum = Integer.parseInt(scanner.next());
                switch (choiceNum) {
                    case 0:
                        break OUTER;
                    case 1:
                        System.out.println(acc1.toString());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        acc1.deposit(Double.parseDouble(scanner.next()));
                        System.out.println(acc1.toString());
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        acc1.withdraw(Double.parseDouble(scanner.next()));
                        System.out.println(acc1.toString());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
