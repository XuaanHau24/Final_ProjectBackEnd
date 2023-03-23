package com.vti.Service;

import com.vti.Entity.Account;
import com.vti.Form.CreatingAccountForm;
import com.vti.Form.UpdatingAccountForm;
import com.vti.filter.AccountFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IAccountService extends UserDetailsService {
    Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form);

    Account getAccountByUsername(String username);

    boolean isAccountExistsByUsername(String username);

    boolean isAccountExistsById(Integer id);

    void createAccount(CreatingAccountForm form);

    void updateAccount(UpdatingAccountForm form);
    void deleteAccount(int id);

    boolean isAccountExistsByEmail(String email);

    Account getAccountById(int id);

    void deleteAccounts(List<Integer> ids);
}
