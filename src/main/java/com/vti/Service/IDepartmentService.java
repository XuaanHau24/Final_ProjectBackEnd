package com.vti.Service;

import com.vti.Entity.Department;
import com.vti.Form.CreatingDepartmentForm;
import com.vti.Form.UpdatingDepartmentForm;
import com.vti.filter.DepartmentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {

    Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm form);

    boolean isDepartmentExistsByName(String name);

    void createDepartment(CreatingDepartmentForm form);

    void updateDepartment(UpdatingDepartmentForm form);

    void deleteDepartment(int id);

    Department getDepartmentById(int id);

    void deleteDepartments(List<Integer> ids);

    boolean isDepartmentExistsById(Integer id);
}
