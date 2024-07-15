package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Attendance;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.Score;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
    List<Attendance> findAttendanceBySchoolId(Long schoolId);
    List<Attendance> findAttendanceByStudentId(Long studentId);
    List<Attendance> findAttendanceBySubjectId(Long subjectId);

    List<Attendance> findAttendanceBySectionId(Long sectionId);
}
