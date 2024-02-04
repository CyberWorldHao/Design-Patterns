/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6.a;

/**
 *
 * @author CWH
 */
class Customer {

    public static final String US = "US", CANADA = "Canada";
    private String address, name, zip, state, type;

    public boolean isValidAddress() {
        // get an appropriate address validator
        AddressValidator validator;
        // Polymorphic call to validate the address
        if (getValidator(type) == null) {
            // Class Adapter
            System.out.println("*** Applying Class Adapter ***");
            validator = new ClassCAAddressAdapter();
            // Object Adapter
//            System.out.println("*** Applying Object Adapter ***");
//            validator = new ObjectCAAddressAdapter(new CAAddress());
        } else {
            validator = getValidator(type);
        }
        return validator.isValidAddress(address, zip, state);
    }

    private AddressValidator getValidator(String custType) {
        AddressValidator validator = null;
        if (custType.equals(Customer.US)) {
            validator = new USAddress();
        }
        return validator;
    }

    public Customer(String inp_name, String inp_address, String inp_zip, String inp_state, String inp_type) {
        name = inp_name;
        address = inp_address;
        zip = inp_zip;
        state = inp_state;
        type = inp_type;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

}

interface AddressValidator {

    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state);
}

class USAddress implements AddressValidator {

    @Override
    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state) {
        if (inp_address.trim().length() < 10) {
            return false;
        }
        if (inp_zip.trim().length() < 5 || inp_zip.trim().length() > 10) {
            return false;
        }
        if (inp_state.trim().length() != 2) {
            return false;
        }
        return true;
    }

}

class CAAddress {

    public boolean isValidCanadianAddr(String inp_address, String inp_pcode, String inp_prvnc) {
        if (inp_address.trim().length() < 15) {
            return false;
        }
        if (inp_pcode.trim().length() != 6) {
            return false;
        }
        if (inp_prvnc.trim().length() < 6) {
            return false;
        }
        return true;
    }
}

class ClassCAAddressAdapter extends CAAddress implements AddressValidator {

    @Override
    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state) {
        return isValidCanadianAddr(inp_address, inp_zip, inp_state);
    }
}

class ObjectCAAddressAdapter implements AddressValidator {

    CAAddress caAddress;

    public ObjectCAAddressAdapter(CAAddress caAddress) {
        this.caAddress = caAddress;
    }

    @Override
    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state) {
        return caAddress.isValidCanadianAddr(inp_address, inp_zip, inp_state);
    }
}

public class AdapterAddressValidationApplication {

    public static void main(String[] args) {
        Customer customer = new Customer("Google", "1600 Amphitheatre Parkway", "94043", "CA", "US");
        Customer customer2 = new Customer("Google", "1600 Amphitheatre Parkway", "94043", "CA", "Canada");
        showAddressValidationResult(customer);
        showAddressValidationResult(customer2);
    }

    public static void showAddressValidationResult(Customer customer) {
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Zip/PostalCode: " + customer.getZip());
        System.out.println("State/Province: " + customer.getState());
        System.out.println("Address Type: " + customer.getType());
        if (customer.isValidAddress()) {
            System.out.println("Result: Valid customer data\n");
        } else {
            System.out.println("Result: Invalid customer data\n");
        }
    }

}
