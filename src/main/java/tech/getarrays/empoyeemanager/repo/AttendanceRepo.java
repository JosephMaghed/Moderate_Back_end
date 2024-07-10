package tech.getarrays.empoyeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.empoyeemanager.model.Attendance;
import tech.getarrays.empoyeemanager.model.Employee;

public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
}
