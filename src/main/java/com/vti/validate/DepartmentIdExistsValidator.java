package com.vti.validate;

import com.vti.Service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentIdExistsValidator implements ConstraintValidator<DepartmentIdExists, Integer> {

    @Autowired
    IDepartmentService departmentService;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return departmentService.isDepartmentExistsById(id);
    }
}
