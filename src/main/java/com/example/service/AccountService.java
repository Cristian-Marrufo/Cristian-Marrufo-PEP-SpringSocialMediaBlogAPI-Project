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
      accountRepository.save(newAccount);
      return verifyAccountExists(newAccount);
    }
    return null;
  }

  public Account verifyLogin(Account userAccount) {
    String username = userAccount.getUsername();
    String password = userAccount.getPassword();
    Optional<Account> optionalAccount = accountRepository.findByUsername(userAccount.getUsername());

    if(optionalAccount.isPresent()) {
      Account retrievedAccount = optionalAccount.get();
      String retrievedUsername = retrievedAccount.getUsername();
      String retrievedPassword = retrievedAccount.getPassword();

      if((retrievedUsername.equals(username)) && (retrievedPassword.equals(password))) {
        return retrievedAccount;
      }
    }
    return null;
  }

  public Account verifyAccountExists(Account account) {
    Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());

    if(optionalAccount.isPresent()) {
      Account verifiedAccount = optionalAccount.get();
      return verifiedAccount;
    }
    return null;
  }
}
