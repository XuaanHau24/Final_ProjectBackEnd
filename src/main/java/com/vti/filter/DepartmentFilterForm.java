package com.vti.filter;

import com.vti.Entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class DepartmentFilterForm {

    private String search;

    private Type type;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createDate;

    private Integer minYear;

    private Integer maxYear;

}
