package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Authority;

public interface AuthorityRepo extends JpaRepository<Authority,Long> {

}
