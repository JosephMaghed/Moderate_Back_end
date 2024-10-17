package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.moderate.model.Score;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score,Long> {
    List<Score> findScoreBySchoolId(Long schoolId);
    List<Score> findScoreByStudentId(Long studentId);
    List<Score> findScoreBySubjectId(Long subjectId);

    List<Score> findScoreBySectionId(Long sectionId);

    @Query("SELECT s FROM Score s WHERE s.student.id = :studentId AND s.subject.id = :subjectId")
    List<Score> findScoresByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);






}
