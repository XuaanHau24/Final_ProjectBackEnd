package com.vti.Specification;

import com.vti.Entity.Department;
import com.vti.filter.DepartmentFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class DepartmentSpecification {
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String TYPE = "type";
    private static final String CREATE_DATE = "createDate";
    private static final String MIN_YEAR = "minYear";
    private static final String MAX_YEAR = "maxYear";

    public static Specification<Department> buildWhere(DepartmentFilterForm form) {
        if (form == null) {
            return null;
        }
        CustomSpecification whereByDepartmentName = new CustomSpecification(DEPARTMENT_NAME, form.getSearch());
        CustomSpecification whereByType = new CustomSpecification(TYPE, form.getType());
        CustomSpecification whereByCreateDate = new CustomSpecification(CREATE_DATE, form.getCreateDate());
        CustomSpecification whereByMinYear = new CustomSpecification(MIN_YEAR, form.getMinYear());
        CustomSpecification whereByMaxYear = new CustomSpecification(MAX_YEAR, form.getMaxYear());
        return Specification.where(whereByDepartmentName).or(whereByType).or(whereByCreateDate).or(whereByMinYear).or(whereByMaxYear);
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<Department> {
        private String key;
        private Object value;


        @Override
        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {
                case DEPARTMENT_NAME:
                    return criteriaBuilder.like(root.get("name"), "%" + value.toString().trim() + "%");
                case TYPE:
                    return criteriaBuilder.equal(root.get("type"), value);
                case CREATE_DATE:
                    return criteriaBuilder.equal(root.get("createDate").as(java.sql.Date.class), value);

                case MIN_YEAR:
                    // WHERE YEAR(createdDate) >= value
                    return criteriaBuilder.greaterThanOrEqualTo(
                            criteriaBuilder.function("YEAR", Integer.class, root.get("createDate")),
                            (Integer) value
                    );
                case MAX_YEAR:
                    // WHERE YEAR(createdDate) <= value
                    return criteriaBuilder.lessThanOrEqualTo(
                            criteriaBuilder.function("YEAR", Integer.class, root.get("createDate")),
                            (Integer) value
                    );

                default:
                    return null;
            }
        }
    }
}
