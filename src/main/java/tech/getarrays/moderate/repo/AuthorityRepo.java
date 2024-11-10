package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.moderate.model.Authority;

import java.util.List;

public interface AuthorityRepo extends JpaRepository<Authority,Long> {
    List<Authority> findAuthorityByJobRoleId(Long JobRoleId);
    List<Authority> findAuthorityByEmployeeId(Long employeeId);
    @Query("SELECT a.authorityName FROM Authority a WHERE a.jobRole.id = :jobRoleId")
    List<String> findAuthorityNamesByJobRoleId(@Param("jobRoleId") Long jobRoleId);

    @Query("SELECT a.authorityName FROM Authority a WHERE a.employee.id = :employeeId")
    List<String> findAuthorityNameByEmployeeId(@Param("employeeId") Long employeeId);
    List<Authority> findAuthorityBySchoolId(Long schoolId);




}
