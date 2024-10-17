package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.moderate.model.Grade;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade,Long> {
    List<Grade> findGradeBySchoolId(Long schoolId);



}
