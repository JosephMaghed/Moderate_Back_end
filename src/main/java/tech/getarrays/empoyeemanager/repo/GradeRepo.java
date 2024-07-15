package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.Grade;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade,Long> {
    List<Grade> findGradeBySchoolId(Long schoolId);

}
