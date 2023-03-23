package com.vti.validate;

import com.vti.Service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameNotExistsValidator implements ConstraintValidator<UsernameNotExists, String> {
    @Autowired
    private IAccountService accountService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !accountService.isAccountExistsByUsername(username);
    }
}
