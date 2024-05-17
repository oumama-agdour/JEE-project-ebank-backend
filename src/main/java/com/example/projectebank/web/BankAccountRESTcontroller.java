package com.example.projectebank.web;

import com.example.projectebank.dtos.AccountHistoryDTO;
import com.example.projectebank.dtos.AccountOperationDTO;
import com.example.projectebank.dtos.BankAccountDTO;
import com.example.projectebank.exceptions.BankAccountNotFound;
import com.example.projectebank.sevices.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountRESTcontroller {
    private BankAccountService bankAccountService;

    public BankAccountRESTcontroller(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountID}")
    public BankAccountDTO getBankAccount(@PathVariable(name = "accountID") String bankAccountId) throws BankAccountNotFound {
        return bankAccountService.getBankAccount(bankAccountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> getAllBankAccounts() {
        return bankAccountService.listBankAccounts();
    }

    @GetMapping("/accounts/{id}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable("id") String accountID){
        return bankAccountService.accountOperationHistory(accountID);
    }

    @GetMapping("/accounts/{id}/pageOperations")
    public AccountHistoryDTO getHistory(@PathVariable("id") String accountID,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFound {
        return bankAccountService.getAccountHistory(accountID, page, size);
    }

}
