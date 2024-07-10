package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Section;

public interface SectionRepo extends JpaRepository<Section,Long> {
}
