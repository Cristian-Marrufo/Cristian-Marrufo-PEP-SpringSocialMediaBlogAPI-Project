package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;
import com.example.entity.Account;
import java.util.*;

@Service
public class AccountService {
  private AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account registerAccount(Account newAccount) {
    String username = newAccount.getUsername();
    int passLength = (newAccount.getPassword()).length();

    if((username != "") && (passLength >= 4)) {
      return accountRepository.save(newAccount);
    }
    return null;
  }

  public Account verifyLogin(Account userAccount) {
    Account retrievedAccount = verifyAccountUserExists(userAccount);

    if(retrievedAccount != null) {
      String password = userAccount.getPassword();
      String retrievedPassword = retrievedAccount.getPassword();

      if(retrievedPassword.equals(password)) {
        return retrievedAccount;
      }
    }
    return null;
  }

  public Account verifyAccountUserExists(Account account) {
    Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());

    if(optionalAccount.isPresent()) {
      Account verifiedAccount = optionalAccount.get();
      return verifiedAccount;
    }
    return null;
  }

  public Account verifyAccountIdExists(Integer accountId) {
    Optional<Account> optionalAccount = accountRepository.findById(accountId);

    if(optionalAccount.isPresent()) {
      Account verifiedAccount = optionalAccount.get();
      return verifiedAccount;
    }
    return null;
  }
}
