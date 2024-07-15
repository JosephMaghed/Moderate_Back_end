package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.Subject;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject,Long> {
    List<Subject> findSubjectBySchoolId(Long subjectId);
    List<Subject> findSubjectByGradeId(Long gradeId);
    List<Subject> findSubjectByEmployeeId(Long gradeId);



}
