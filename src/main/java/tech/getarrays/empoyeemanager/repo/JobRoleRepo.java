package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.JobRole;

public interface JobRoleRepo extends JpaRepository<JobRole,Long> {

}
