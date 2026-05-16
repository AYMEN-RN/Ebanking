package org.sid.ebankingbackend.services;

import org.sid.ebankingbackend.dtos.*;
import org.sid.ebankingbackend.exceptions.BalanceNotSufficientExcepion;
import org.sid.ebankingbackend.exceptions.BankAccountNotFound;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(int customerId) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(int customerId);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft , Integer customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Integer customerId) throws CustomerNotFoundException;
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFound;
    List<BankAccountDTO> bankAccountList();

    void debit(String accountId,double amount,String description) throws BankAccountNotFound, BalanceNotSufficientExcepion;
    void credit(String accountId,double amount,String description) throws BankAccountNotFound;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficientExcepion, BankAccountNotFound;

    List<AccountOperationDTO> accountHistorique(String accountId);

    AccountHistoryDTO getAccountHistorique(String accountId, int page, int size) throws BankAccountNotFound;
}
