package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRespository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private static final AccountRespository accountRespository = new AccountRespository();

    public boolean addAccount(Account account){
        return accountRespository.addAccount(account);
    }

    public Optional<Account> findAccount(String id){
        return accountRespository.findAccount(id);
    }

    public Optional<Account> updateAccount(Account updatedAccount){
        return accountRespository.updateAccount(updatedAccount);
    }

    public Optional<Boolean> deleteAccountById(String accountId) {
        return accountRespository.deleteAccountById(accountId);
    }

    public List<Account> getAll() {
        return accountRespository.getAll();
    }

    public Optional<Account> checkLogin(String username, String password) {
        return accountRespository.checkLogin(username, password);
    }
}
