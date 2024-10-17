package tech.getarrays.moderate.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.moderate.model.Attendance;
import tech.getarrays.moderate.model.StudentAttendanceDTO;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
    List<Attendance> findAttendanceBySchoolId(Long schoolId);
    List<Attendance> findAttendanceByStudentId(Long studentId);
    List<Attendance> findAttendanceBySubjectId(Long subjectId);

    List<Attendance> findAttendanceBySectionId(Long sectionId);
    @Query("SELECT s FROM Attendance s WHERE s.student.id = :studentId AND s.subject.id = :subjectId")
    List<Attendance> findAttendanceByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
    @Query("SELECT StudentAttendanceDTO(st.id, st.name, " +
            "SUM(CASE WHEN at.attended = TRUE THEN 1 ELSE 0 END)) " +
            "FROM Attendance at " +
            "INNER JOIN Student st ON at.student.id = st.id " +
            "WHERE st.id = :studentId " +
            "GROUP BY st.id, st.name")
    List<StudentAttendanceDTO> findStudentAttendanceById(Long studentId);
}
