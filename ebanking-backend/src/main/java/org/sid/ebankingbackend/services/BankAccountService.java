package org.sid.ebankingbackend.services;

import org.sid.ebankingbackend.dtos.CustomerDTO;
import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.Customer;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.exceptions.BalanceNotSufficientExcepion;
import org.sid.ebankingbackend.exceptions.BankAccountNotFound;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft , Integer customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Integer customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFound;
    void debit(String accountId,double amount,String description) throws BankAccountNotFound, BalanceNotSufficientExcepion;
    void credit(String accountId,double amount,String description) throws BankAccountNotFound;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficientExcepion, BankAccountNotFound;

    List<BankAccount> bankAccountList();
}
