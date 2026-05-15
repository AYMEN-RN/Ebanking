package org.sid.ebankingbackend.services;

import jakarta.transaction.Transactional;
import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){

        BankAccount bankAccount = bankAccountRepository.findById("1e54cf51-3287-4223-9f1d-d3f12d85d7b5").orElse(null);
        System.out.println("****************");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCustomer().getName());
        System.out.println(bankAccount.getCustomer().getEmail());
        if (bankAccount instanceof CurrentAccount) {
            System.out.println("OverDraft => "+((CurrentAccount)bankAccount).getOverDraft());
        }else if (bankAccount instanceof SavingAccount) {
            System.out.println("IntersetRate => "+((SavingAccount)bankAccount).getInterestRate());
        }
        bankAccount.getAccountOperations().forEach(
                operation -> {
                    System.out.print(operation.getType()+"\t");
                    System.out.print(operation.getAmount()+"\t");
                    System.out.print(operation.getOperationDate());
                    System.out.println();
                });
    }
}
