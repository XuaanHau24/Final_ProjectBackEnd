package com.vti.Service;

import com.vti.Entity.Account;
import com.vti.Form.CreatingAccountForm;
import com.vti.Form.UpdatingAccountForm;
import com.vti.Repository.IAccountRepository;
import com.vti.Repository.IDepartmentRepository;
import com.vti.Specification.AccountSpecification;
import com.vti.filter.AccountFilterForm;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    //get all account
    @Override
    public Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form) {
        Specification<Account> specification = AccountSpecification.buildWhere(form);
            return accountRepository.findAll(specification, pageable);
    }

    //get account by user
    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public boolean isAccountExistsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public boolean isAccountExistsById(Integer id) {
        return accountRepository.existsById(id);
    }

    //create account
    @Override
    public void createAccount(CreatingAccountForm form) {
        TypeMap<CreatingAccountForm, Account> typeMap = modelMapper.getTypeMap(CreatingAccountForm.class, Account.class);
        if (typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreatingAccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Account account = modelMapper.map(form, Account.class);
        account.setDepartment(departmentRepository.findById(form.getDepartmentId()));
        accountRepository.save(account);
    }

    //update account
    @Override
    public void updateAccount(UpdatingAccountForm form) {
        Account account = modelMapper.map(form, Account.class);
        account.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        accountRepository.save(account);
    }

    //delete 1 account
    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public boolean isAccountExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteAccounts(List<Integer> ids) {
        accountRepository.deleteById(ids);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(username, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }
}
