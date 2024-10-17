package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.moderate.model.JobRole;

import java.util.List;

public interface JobRoleRepo extends JpaRepository<JobRole,Long> {
    List<JobRole> findJobRoleBySchoolId(Long schoolId);

}
