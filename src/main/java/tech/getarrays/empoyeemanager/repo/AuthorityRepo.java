package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Authority;
import tech.getarrays.empoyeemanager.model.Employee;

import java.util.List;

public interface AuthorityRepo extends JpaRepository<Authority,Long> {
    List<Authority> findAuthorityByJobRoleId(Long JobRoleId);
    List<Authority> findAuthorityByEmployeeId(Long employeeId);
    List<Authority> findAuthorityBySchoolId(Long schoolId);




}
