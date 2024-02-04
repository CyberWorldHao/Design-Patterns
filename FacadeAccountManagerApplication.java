/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6.b;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author CWH
 */
class Account {

    String firstName, lastName;
    final String ACCOUNT_DATA_FILE = "AccountData.txt";

    public Account(String fname, String lname) {
        firstName = fname;
        lastName = lname;
    }

    public boolean isValid() {
        /*
            Let's go with simpler validation
            here to keep the example simpler.
         */

        if (getLastName().trim().length() < 2) {
            return false;
        }
        return true;
    }

    public boolean save() {
        FileUtility futil = new FileUtility();
        String dataLine = getLastName() + "," + getFirstName();
        return futil.writeToFile(ACCOUNT_DATA_FILE, dataLine);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}

class Address {

    String address, city, state;
    final String ADDRESS_DATA_FILE = "AddressData.txt";

    public Address(String add, String cty, String st) {
        address = add;
        city = cty;
        state = st;
    }

    public boolean isValid() {
        /*
            The address validation algorithm could be complex in real-world
            applications. Let's go with simpler validation here to keep the exmple simpler.
         */
        if (getState().trim().length() < 2) {
            return false;
        }
        return true;
    }

    public boolean save() {
        FileUtility futil = new FileUtility();
        String dataLine = getAddress() + "," + getCity() + "," + getState();
        return futil.writeToFile(ADDRESS_DATA_FILE, dataLine);
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

}

class CreaditCard {

    String cardType, cardNumber, cardExpDate;
    final String CC_DATA_FILE = "CC.txt";
    public static final String VISA = "Visa", MASTER = "Master";

    public CreaditCard(String ccType, String ccNumber, String ccExpDate) {
        this.cardType = ccType;
        this.cardNumber = ccNumber;
        this.cardExpDate = ccExpDate;
    }

    public boolean isValid() {
        /*
            Let's go with simpler validation here to keep the exmple simpler.
         */
        if (getCardType().equals(VISA) || getCardType().equals(MASTER)) {
            return (getCardNumber().trim().length() == 16);
        }
        return false;
    }

    public boolean save() {
        FileUtility futil = new FileUtility();
        String dataLine = getCardType() + "," + getCardNumber() + "," + getCardExpDate();
        return futil.writeToFile(CC_DATA_FILE, dataLine);
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpDate() {
        return cardExpDate;
    }

}

class FileUtility {

    public boolean writeToFile(String filename, String msg) {
        boolean isWriteToFileSuccess = false;
        try {
            File fileObj = new File(filename);
//            if (fileObj.createNewFile()) {
//                System.out.println("File created: " + fileObj.getName());
//            }
//            System.out.println("Writing data line to " + filename + " . . . . . .");
            try (FileWriter fileWriter = new FileWriter(filename)) {
                fileWriter.write(msg);
            }
            isWriteToFileSuccess = true;
//            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Write to file error occurred.");
        }
        return isWriteToFileSuccess;
    }
}

class FacadeAccountManager {

    CreaditCard cc;
    Account acc;
    Address addr;

    public FacadeAccountManager(Account acc, Address addr, CreaditCard cc) {
        this.cc = cc;
        this.acc = acc;
        this.addr = addr;
    }

    public boolean validateInputData() {
        boolean savable = true;
        System.out.println("First name = " + acc.getFirstName() + ", Last name = " + acc.getLastName());
        System.out.println("Address = " + addr.getAddress() + ", City = " + addr.getCity() + ", State = " + addr.getState());
        System.out.println("Card type = " + cc.getCardType() + ", Card number = " + cc.getCardNumber() + ", Card expiry date = " + cc.getCardExpDate());
        if (acc.isValid()) {
            System.out.println("Valid FirstName/LastName");
        } else {
            savable = false;
            System.out.println("Invalid FirstName/LastName");
        }
        if (addr.isValid()) {
            System.out.println("Valid Address/City/State");
        } else {
            savable = false;
            System.out.println("Invalid Address/City/State");
        }
        if (cc.isValid()) {
            System.out.println("Valid CreditCard");
        } else {
            savable = false;
            System.out.println("Invalid CreditCard");
        }
        return savable;
    }

    public void saveInputData() {
        if (validateInputData()) {
            acc.save();
            addr.save();
            cc.save();
            System.out.println("==>Valid Customer Data: Data Saved Successfully");
        } else {
            System.out.println("==>>Invalid Customer Data: Data Could Not Be Saved");
        }
    }
}

public class FacadeAccountManagerApplication {

    public static void main(String[] args) {
        Account[] accs = {new Account("John", "Smith"), new Account("Vijaya", "K"), new Account("Aryati", "Ahmad")};
        Address[] addres = {new Address("101 Jalan Bukit", "Shah Alam", "Selangor"), new Address("1 Jalan University", "Kuala Lumpur", "Wilayah Persekutuan"), new Address("35 Wisma Jaya", "Petaling Jaya", "Selangor")};
        CreaditCard[] ccs = {new CreaditCard("Visa", "1111222233334444", "01/09/2020"), new CreaditCard("Master", "9999888877776666", "01/01/2022"), new CreaditCard("Master", "555566667777", " 01/05/2023")};
        FacadeAccountManager facadeAccountManager;

        for (int i = 0; i < accs.length; i++) {
            switch (i) {
                case 0:
                    System.out.println("First customer: ");
                    break;
                case 1:
                    System.out.println("Second customer: ");
                    break;
                case 2:
                    System.out.println("Third customer: ");
                    break;
            }
            facadeAccountManager = new FacadeAccountManager(accs[i], addres[i], ccs[i]);
            facadeAccountManager.saveInputData();
            System.out.println("");
        }

    }

}
