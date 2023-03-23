package com.vti.filter;

import com.vti.Entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {

    private String search;

    private Integer departmentId;

    private Role role;
}
