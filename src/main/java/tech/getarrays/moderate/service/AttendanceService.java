package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.model.Attendance;
import tech.getarrays.moderate.repo.AttendanceRepo;

import java.util.List;

@Service
public class AttendanceService {
    private  final AttendanceRepo attendanceRepoRepo;

    //Constructor
    @Autowired
    public AttendanceService( AttendanceRepo attendanceRepoRepo) {
        this.attendanceRepoRepo = attendanceRepoRepo;
    }

    public Attendance addAttendance(Attendance attendance){
        return  attendanceRepoRepo.save(attendance);
    }

    public List<Attendance> findAllAuthorities(){
        return attendanceRepoRepo.findAll();
    }

    public Attendance findAttendanceById(Long id){
        return attendanceRepoRepo.findById(id).orElse(null);}

    public Attendance updateAttendance(Attendance Attendance){
        return  attendanceRepoRepo.save(Attendance);
    }

    public void deleteAttendance(Long id){
        attendanceRepoRepo.deleteById(id);
    }

    public List<Attendance> getAttendanceByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return attendanceRepoRepo.findAttendanceByStudentIdAndSubjectId(studentId, subjectId);
    }
}
