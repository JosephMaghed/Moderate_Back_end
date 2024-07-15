package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Authority;
import tech.getarrays.empoyeemanager.model.JobRole;

import java.util.List;

public interface JobRoleRepo extends JpaRepository<JobRole,Long> {
    List<JobRole> findJobRoleBySchoolId(Long schoolId);

}
