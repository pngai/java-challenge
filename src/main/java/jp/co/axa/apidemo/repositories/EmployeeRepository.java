package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Modifying
    @Query(value = "DELETE FROM Employee e WHERE e.id = :employeeId")
    int deleteByEmployeeId(@Param("employeeId") Long employeeId);
}
