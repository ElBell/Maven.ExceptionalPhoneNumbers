package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];
        for (int i = 0; i < phoneNumbers.length; i++) {
            phoneNumbers[i] = createRandomPhoneNumber();
        }
        return phoneNumbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() {
        return createPhoneNumberSafely(randomNum(3), randomNum(3), randomNum(4));
    }

    private static int randomNum(int digits) {
        Random r = new Random();
        return digits == 3 ? r.nextInt((999 - 100) + 1) + 100 : r.nextInt((9999 - 1000) + 1) + 1000;
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String area = String.valueOf(areaCode);
        String officeCode = String.valueOf(centralOfficeCode);
        String lineCode = String.valueOf(phoneLineCode);
        if(area.length() == 3 && officeCode.length() == 3 && lineCode.length() == 4) {
            try {
                return createPhoneNumber(String.format("(%s)-%s-%s", area, officeCode, lineCode));
            } catch (InvalidPhoneNumberFormatException e) {
                logger.log(Level.INFO, String.format("(%s)-%s-%s is not a valid phone number", area, officeCode, lineCode));
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        try {
            logger.log(Level.INFO, "Attempting to create a new PhoneNumber object with a value of" + phoneNumberString);
            return new PhoneNumber(phoneNumberString);
        } catch (InvalidPhoneNumberFormatException e) {
            throw new InvalidPhoneNumberFormatException();
        }
    }
}
