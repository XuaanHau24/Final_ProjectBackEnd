package com.vti.Repository;

import com.vti.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
    Account findById(int id);

    Account findByEmail(String email);

    Account findByToken(String token);

    @Modifying
    @Transactional
    @Query("DELETE FROM Account WHERE id IN (:ids)")
    void deleteById(@Param("ids") List<Integer> ids);
}
