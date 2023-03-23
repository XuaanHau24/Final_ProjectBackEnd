package com.vti.Repository;

import com.vti.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    boolean existsByName(String name);

    Department findById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Department WHERE id IN (:ids)")
    void deleteById(@Param("ids") List<Integer> ids);
}
