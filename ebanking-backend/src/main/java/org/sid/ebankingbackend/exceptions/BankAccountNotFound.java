package org.sid.ebankingbackend.exceptions;

public class BankAccountNotFound extends Exception {
    public BankAccountNotFound(String message) {
        super(message);
    }
}
