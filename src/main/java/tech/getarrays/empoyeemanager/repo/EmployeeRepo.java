package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Employee;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    List<Employee> findEmployeeBySchoolId(Long schoolId);
    List<Employee> findEmployeeByJobRoleId(Long schoolId);


}
