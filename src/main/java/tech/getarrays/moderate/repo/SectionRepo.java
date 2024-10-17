package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.moderate.model.Section;

import java.util.List;

public interface SectionRepo extends JpaRepository<Section,Long> {
    List<Section> findAuthorityByGradeId(Long gradeId);
    List<Section> findSectionBySchoolId(Long schoolId);


}
