package com.vti.Controller;

import com.vti.DTO.DepartmentDTO;
import com.vti.Entity.Department;
import com.vti.Form.CreatingDepartmentForm;
import com.vti.Form.UpdatingDepartmentForm;
import com.vti.Service.IDepartmentService;
import com.vti.filter.DepartmentFilterForm;
import com.vti.validate.AccountIdExists;
import com.vti.validate.DepartmentIdExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/departments")
@Validated
@CrossOrigin("*")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;


    //get all department
    @GetMapping
    public ResponseEntity<Page<DepartmentDTO>> getAllDepartments(Pageable pageable, DepartmentFilterForm form){
        Page<Department> page = departmentService.getAllDepartments(pageable, form);
        List<DepartmentDTO> departmentDTOS = modelMapper.map(page.getContent(), new TypeToken<List<DepartmentDTO>>(){
        }.getType());
        return new ResponseEntity<>(new PageImpl<>(departmentDTOS, pageable, page.getTotalElements()), HttpStatus.OK);
    }


    // get department by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "id") @AccountIdExists int id) {
        return new ResponseEntity<>(modelMapper.map(departmentService.getDepartmentById(id), DepartmentDTO.class), HttpStatus.OK);
    }

    //create department
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody @Valid CreatingDepartmentForm form) {
        departmentService.createDepartment(form);
        return new ResponseEntity<>("Create department successfully",HttpStatus.OK);
    }

    //update department
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable(name = "id")  @DepartmentIdExists int id, @RequestBody @Valid UpdatingDepartmentForm form) {
        form.setId(id);
        departmentService.updateDepartment(form);
        return new ResponseEntity<>("Update department successfully",HttpStatus.OK);
    }

    //delete department
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "id") @AccountIdExists int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Delete department successfully",HttpStatus.OK);
    }

    //delete list departments
    @DeleteMapping
    public ResponseEntity<?> deleteDepartments(@RequestParam(name = "ids") List<Integer> ids){
        departmentService.deleteDepartments(ids);
        return new ResponseEntity<>("Delete list department successfully",HttpStatus.OK);
    }


}
