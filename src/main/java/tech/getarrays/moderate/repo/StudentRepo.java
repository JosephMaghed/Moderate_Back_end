package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.moderate.model.Student;
import tech.getarrays.moderate.model.StudentAttendanceDTO;
import tech.getarrays.moderate.model.StudentScoreDTO;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {
    List<Student> findStudentBySchoolId(Long schoolId);
    List<Student> findStudentBySectionId(Long sectionId);
    List<Student> findStudentByGradeId(Long sectionId);
    @Query("SELECT StudentScoreDTO(st.id, st.name, " +
            "SUM((sc.studentScore / sc.fullMark) * sc.ScoreWeightPercentage)) " +
            "FROM Score sc " +
            "INNER JOIN student st " +
            "WHERE st.id = :studentId AND sc.fullMark > 0 " +
            "GROUP BY st.id, st.name")
    List<StudentScoreDTO> findStudentScoreById(@Param("studentId") Long studentId);


    @Query("SELECT StudentAttendanceDTO(st.id, st.name, " +
            "SUM(CASE WHEN at.attended = TRUE THEN 1 ELSE 0 END)) " +
            "FROM Attendance at " +
            "INNER JOIN Student st ON at.student.id = st.id " +
            "WHERE st.id = :studentId " +
            "GROUP BY st.id, st.name")
    List<StudentAttendanceDTO> findStudentAttendanceById(Long studentId);



}
