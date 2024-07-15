package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {
    List<Student> findStudentBySchoolId(Long schoolId);
    List<Student> findStudentBySectionId(Long sectionId);


}
