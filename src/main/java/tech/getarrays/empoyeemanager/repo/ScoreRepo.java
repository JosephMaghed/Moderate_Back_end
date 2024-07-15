package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Attendance;
import tech.getarrays.empoyeemanager.model.Score;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score,Long> {
    List<Score> findScoreBySchoolId(Long schoolId);
    List<Score> findScoreByStudentId(Long studentId);
    List<Score> findScoreBySubjectId(Long subjectId);

    List<Score> findScoreBySectionId(Long sectionId);





}
