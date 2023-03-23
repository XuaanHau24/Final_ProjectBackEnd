package com.vti.Form;

import com.vti.validate.NameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class UpdatingDepartmentForm {

    private int id;

    @NotBlank(message = "{Department.createDepartment.form.name.NotBlank}")
    @Length(max = 50, message = "{Department.createDepartment.form.name.Length}")
    @NameNotExists
    private String name;

    @Positive
    private int totalMember;
}
