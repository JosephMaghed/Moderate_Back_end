package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.moderate.model.School;

public interface SchoolRepo extends JpaRepository<School,Long> {
}
