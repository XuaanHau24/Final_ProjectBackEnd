package com.vti.Form;

import com.vti.Entity.Role;
import com.vti.validate.EmailNotExists;
import com.vti.validate.NameNotExists;
import com.vti.validate.UsernameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
public class CreatingDepartmentForm {

    @NotBlank(message = "{Department.createDepartment.form.name.NotBlank}")
    @Length(max = 50, message = "{Department.createDepartment.form.name.Length}")
    @NameNotExists
    private String name;

    @Positive
    private int totalMember;

    @Pattern(regexp = "Dev|Test|ScrumMaster|PM")
    private String type;

    private List<AccountForm> accounts;
    @Data
    @NoArgsConstructor
    public static class AccountForm{
        private Integer id;
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

        @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
        private Role role;

        @NotBlank(message = "{Account.createAccount.form.password.NotBlank}")
        @Length(max = 800, message = "{Account.createAccount.form.password.Length}")
        private String password;

        @Positive
        private int departmentId;
    }
}
