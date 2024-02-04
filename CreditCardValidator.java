/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab8.CreditCardValidator;

/**
 *
 * @author CWH
 */
import java.util.Calendar;

abstract class ValidationAlgo {

    private Calendar cal;
    private int month, year;
    private boolean isValid;
    private String cardNum;

    final void ISVALID(String cardNum, int expiryMonth, int expiryYear) {
        isValid = true;
        cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        this.cardNum = cardNum;

        System.out.print(isValidExpirationDate(expiryMonth, expiryYear) ? "" : "Invalid Expiry Date.\n");
        isValid = checkLength() && isValid;
        isValid = checkPrefix() && isValid;
        System.out.print(isValidCharacters() ? "" : "Invalid Characters.\n");
        System.out.print(isValidCheckSum() ? "" : "Invalid Check Sum.\n");
        if (isAccountInGoodStand()) {
            isValid = otherChecks() && isValid;
        }
        System.out.println(isValid ? "This credit card is valid.\n" : "This credit card is invalid.\n");
    }

    abstract boolean checkLength();

    abstract boolean checkPrefix();

    abstract boolean otherChecks();

    public boolean isAccountInGoodStand() {
        /*
        Make necessary API calls to
        perform other checks.
         */
        return true;
    }

    private boolean isValidExpirationDate(int expiryMonth, int expiryYear) {
        boolean result = true;
        if (year > expiryYear || year == expiryYear && month > expiryMonth) {
            isValid = false;
            result = false;
        }
        return result;
    }

    private boolean isValidCharacters() {
        boolean result = true;
        String[] characters = cardNum.split("");
        for (String c : characters) {
            int asciiInt = (int) c.charAt(0);
            if (asciiInt < 48 || asciiInt > 57) {
                result = false;
            }
        }
        return result;
    }

    private boolean isValidCheckSum() {
        boolean result = true;
        int sum = 0;
        int multiplier = 1;
        int strLen = cardNum.length();
        for (int i = 0; i < strLen; i++) {
            String digit = cardNum.substring(strLen - i - 1, strLen - i);
            int currProduct = Integer.parseInt(digit) * multiplier;
            if (currProduct >= 10) {
                sum += (currProduct % 10) + 1;
            } else {
                sum += currProduct;
            }
            if (multiplier == 1) {
                multiplier++;
            } else {
                multiplier--;
            }
        }
        if ((sum % 10) != 0) {
            isValid = false;
            result = false;
        }
        return result;
    }
}

class Visa extends ValidationAlgo {

    private final String CARDTYPE = "VisaCard", CARDNUMBER;
    private final int EXPRIRY_MONTH, EXPRIRY_YEAR;

    public Visa(String cardNum, int expriryMonth, int expriryYear) {
        CARDNUMBER = cardNum;
        EXPRIRY_MONTH = expriryMonth;
        EXPRIRY_YEAR = expriryYear;
        System.out.println("Card type = " + CARDTYPE + ", Card number = " + CARDNUMBER + ", Expiry month = " + EXPRIRY_MONTH + ", Expiry year = " + EXPRIRY_YEAR);
    }

    public String getCARDTYPE() {
        return CARDTYPE;
    }

    public String getCARDNUMBER() {
        return CARDNUMBER;
    }

    public int getEXPRIRY_MONTH() {
        return EXPRIRY_MONTH;
    }

    public int getEXPRIRY_YEAR() {
        return EXPRIRY_YEAR;
    }

    @Override
    public boolean checkLength() {
        boolean result = true;
        if (CARDNUMBER.length() != 13 && CARDNUMBER.length() != 16) {
            result = false;
            System.out.println("Invalid Card Number Length.");
        }
        return result;
    }

    @Override
    public boolean checkPrefix() {
        boolean result = true;
        if (CARDNUMBER.charAt(0) != '4') {
            result = false;
            System.out.println("Invalid Prefix.");
        }
        return result;
    }

    @Override
    public boolean otherChecks() {
        /*
        Make use custome Visa API
         */
        return true;
    }
}

class MasterCard extends ValidationAlgo {

    private final String CARDTYPE = "MasterCard", CARDNUMBER;
    private final int EXPRIRY_MONTH, EXPRIRY_YEAR;

    public MasterCard(String cardNum, int expriryMonth, int expriryYear) {
        CARDNUMBER = cardNum;
        EXPRIRY_MONTH = expriryMonth;
        EXPRIRY_YEAR = expriryYear;
        System.out.println("Card type = " + CARDTYPE + ", Card number = " + CARDNUMBER + ", Expiry month = " + EXPRIRY_MONTH + ", Expiry year = " + EXPRIRY_YEAR);
    }

    public String getCARDTYPE() {
        return CARDTYPE;
    }

    public String getCARDNUMBER() {
        return CARDNUMBER;
    }

    public int getEXPRIRY_MONTH() {
        return EXPRIRY_MONTH;
    }

    public int getEXPRIRY_YEAR() {
        return EXPRIRY_YEAR;
    }

    @Override
    public boolean checkLength() {
        boolean result = true;
        if (CARDNUMBER.length() != 16) {
            result = false;
            System.out.println("Invalid Card Number Length.");
        }
        return result;
    }

    @Override
    public boolean checkPrefix() {
        boolean result = true;
        int prefix = Integer.valueOf(CARDNUMBER.substring(0, 2));
        if (prefix < 51 || prefix > 55) {
            result = false;
            System.out.println("Invalid Prefix.");
        }
        return result;
    }

    @Override
    public boolean otherChecks() {
        /*
        Make use custome MasterCard API
         */
        return true;
    }
}

class DinersClub extends ValidationAlgo {

    private final String CARDTYPE = "DinersCard", CARDNUMBER;
    private final int EXPRIRY_MONTH, EXPRIRY_YEAR;

    public DinersClub(String cardNum, int expriryMonth, int expriryYear) {
        CARDNUMBER = cardNum;
        EXPRIRY_MONTH = expriryMonth;
        EXPRIRY_YEAR = expriryYear;
        System.out.println("Card type = " + CARDTYPE + ", Card number = " + CARDNUMBER + ", Expiry month = " + EXPRIRY_MONTH + ", Expiry year = " + EXPRIRY_YEAR);
    }

    public String getCARDTYPE() {
        return CARDTYPE;
    }

    public String getCARDNUMBER() {
        return CARDNUMBER;
    }

    public int getEXPRIRY_MONTH() {
        return EXPRIRY_MONTH;
    }

    public int getEXPRIRY_YEAR() {
        return EXPRIRY_YEAR;
    }

    @Override
    public boolean checkLength() {
        boolean result = true;
        if (CARDNUMBER.length() != 14) {
            result = false;
            System.out.println("Invalid Card Number Length.");
        }
        return result;
    }

    @Override
    public boolean checkPrefix() {
        boolean result = true;
        String prefix = CARDNUMBER.substring(0, 2);
        if (!prefix.equals("30") && !prefix.equals("36") && !prefix.equals("38")) {
            result = false;
            System.out.println("Invalid Prefix.");
        }
        return result;
    }

    @Override
    public boolean otherChecks() {
        /*
        Make use custome Diners Club API
         */
        return true;
    }
}

public class CreditCardValidator {

    public static void main(String[] args) {
        Visa visaCard1 = new Visa("1231234123412341234", 11, 2004);
        visaCard1.ISVALID(visaCard1.getCARDNUMBER(), visaCard1.getEXPRIRY_MONTH(), visaCard1.getEXPRIRY_YEAR());

        Visa visaCard2 = new Visa("1234567890123456", 11, 2020);
        visaCard2.ISVALID(visaCard2.getCARDNUMBER(), visaCard2.getEXPRIRY_MONTH(), visaCard2.getEXPRIRY_YEAR());

        Visa visaCard3 = new Visa("4234567890123456", 11, 2020);
        visaCard3.ISVALID(visaCard3.getCARDNUMBER(), visaCard3.getEXPRIRY_MONTH(), visaCard3.getEXPRIRY_YEAR());

        MasterCard masterCard1 = new MasterCard("5448755330349315", 4, 2021);
        masterCard1.ISVALID(masterCard1.getCARDNUMBER(), masterCard1.getEXPRIRY_MONTH(), masterCard1.getEXPRIRY_YEAR());

        DinersClub dinersCard1 = new DinersClub("30263720264678", 5, 2025);
        dinersCard1.ISVALID(dinersCard1.getCARDNUMBER(), dinersCard1.getEXPRIRY_MONTH(), dinersCard1.getEXPRIRY_YEAR());
    }
}
