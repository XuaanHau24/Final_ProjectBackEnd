package com.vti.Form;

import com.vti.validate.EmailNotExists;
import com.vti.validate.UsernameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class UpdatingAccountForm {

    private int id;
    @NotBlank(message = "{Account.createAccount.form.username.NotBlank}")
    @Length(max = 50, message = "{Account.createAccount.form.username.Length}")
    @UsernameNotExists
    private String username;

    @NotBlank(message = "{Account.createAccount.form.firstName.NotBlank}")
    @Length(max = 50, message = "{Account.createAccount.form.firstName.Length}")
    private String firstName;

    @NotBlank(message = "{Account.createAccount.form.lastName.NotBlank}")
    @Length(max = 50, message = "{Account.createAccount.form.lastName.Length}")
    private String lastName;

    @NotBlank(message = "{Account.createAccount.form.email.NotBlank}")
    @Length(max = 50, message = "{Account.createAccount.form.email.Length}")
    @Email(message = "{Account.createAccount.form.email.Email}")
    @EmailNotExists
    private String email;

    @Positive
    private int departmentId;

    @NotBlank(message = "{Account.createAccount.form.password.NotBlank}")
    @Length(max = 800, message = "{Account.createAccount.form.password.Length}")
    private String password;

    @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
    private String role;
}
