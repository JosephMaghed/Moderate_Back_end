package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.*;
import tech.getarrays.empoyeemanager.service.*;
import tech.getarrays.empoyeemanager.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    //Initialise & define service files
    public AttendanceController(AttendanceService AttendanceService, AttendanceService attendanceService, StudentService studentService, SubjectService subjectService, SchoolService schoolService) {
        this.AttendanceService = AttendanceService;
        this.attendanceService = attendanceService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.schoolService = schoolService;
    }
    private final AttendanceService AttendanceService;
    private final AttendanceService attendanceService;

    private final StudentService studentService;
    private final SubjectService subjectService;

    private final SchoolService schoolService;



    @PostMapping("/add")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance){
        Attendance newAttendance;

        //Retrieve SchoolId & add it to Attendance entity as a foreign key
        if(attendance.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(attendance.getSchool().getId());
            attendance.setSchool(existingSchool);
        }

        //Retrieve StudentId & add it to Attendance entity as a foreign key
        if(attendance.getStudent().getId()!=null){
            Student existingStudent = studentService.findStudentById(attendance.getStudent().getId());
            attendance.setStudent(existingStudent);
        }
        //Retrieve StudentId & add it to Attendance entity as a foreign key
        if(attendance.getSubject().getId()!=null){
            Subject existingSubject = subjectService.findSubjectById(attendance.getSubject().getId());
            attendance.setSubject(existingSubject);
        }

        newAttendance = AttendanceService.addAttendance(attendance);
        return new ResponseEntity<>(newAttendance, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Attendance>> getAllAuthorities(){
        List<Attendance> authorities = AttendanceService.findAllAuthorities();
        return new ResponseEntity<>(authorities,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <Attendance> getAttendanceById(@PathVariable("id")Long id){
       Attendance teams= AttendanceService.findAttendanceById(id);
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Attendance> updateAttendance(@RequestBody Attendance Attendance, @PathVariable("id")Long id){
        Attendance existingAttendance = AttendanceService.findAttendanceById(id);


        //Check if parameters are null if not update accordingly
        if(Attendance.getSubject().getId()!=null){
            Subject existingSubject = subjectService.findSubjectById(Attendance.getSubject().getId());
            Attendance.setSubject(existingSubject);
        }
        if(Attendance.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(Attendance.getSchool().getId());
            Attendance.setSchool(existingSchool);
        }
        if(Attendance.getStudent().getId()!=null){
            Student existingStudent = studentService.findStudentById(Attendance.getStudent().getId());
            Attendance.setStudent(existingStudent);
        }
        if(Attendance.getAttended()!=null){
            existingAttendance.setAttended(Attendance.getAttended());
        }
        if(Attendance.getDate()!=null){
            existingAttendance.setDate(Attendance.getDate());
        }



        Attendance updateAttendance = AttendanceService.updateAttendance(existingAttendance);
        return new ResponseEntity<>(updateAttendance, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteAttendance(@PathVariable("id")Long id){
       AttendanceService.deleteAttendance(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
