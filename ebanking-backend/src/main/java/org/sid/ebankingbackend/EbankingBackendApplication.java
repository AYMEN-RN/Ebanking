package org.sid.ebankingbackend;

import org.sid.ebankingbackend.entities.*;
import org.sid.ebankingbackend.exceptions.BalanceNotSufficientExcepion;
import org.sid.ebankingbackend.exceptions.BankAccountNotFound;
import org.sid.ebankingbackend.exceptions.CustomerNotFoundException;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.sid.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService, BankAccountRepository bankAccountRepository) {
        return args -> {
            Stream.of("Aymen","Brahim","Oussama").forEach(
                    name -> {
                        Customer customer = new Customer();
                        customer.setName(name);
                        customer.setEmail(name + "00@gmail.com");
                        bankAccountService.saveCustomer(customer);
                    });
                    bankAccountService.listCustomers().forEach(cust->{
                        try {
                            bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000,cust.getId());
                            bankAccountService.saveSavingBankAccount(Math.random()*9000,0.2,cust.getId());
                            List<BankAccount> bankAccounts = bankAccountService.bankAccountList();
                            for(BankAccount bankAccount:bankAccounts){
                                    for (int i = 0; i <10 ; i++) {
                                        bankAccountService.credit(bankAccount.getId(),10000+Math.random()*1200,"Credit N"+i);
                                        bankAccountService.debit(bankAccount.getId(),10000+Math.random()*500,"Debit N"+i);
                                    }

                            }
                        } catch (CustomerNotFoundException e) {
                            e.printStackTrace();
                        } catch (BankAccountNotFound | BalanceNotSufficientExcepion e) {
                            e.printStackTrace();
                        }
                    });
        };
    }

        };
