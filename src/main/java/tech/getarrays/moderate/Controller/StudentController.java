package tech.getarrays.moderate.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.moderate.model.*;
import tech.getarrays.moderate.service.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    //Initialise & define service files

    //Constructor
    public StudentController(JobRoleService jobRoleService, StudentService StudentService, SchoolService schoolService, GradeService gradeServiceService, SectionService sectionService, ScoreService scoreService, AttendanceService attendanceService, SubjectService subjectService) {
        this.jobRoleService = jobRoleService;
        this.StudentService = StudentService;
        this.schoolService = schoolService;
        this.gradeServiceService = gradeServiceService;
        this.sectionService = sectionService;
        this.scoreService = scoreService;
        this.attendanceService = attendanceService;
        this.subjectService = subjectService;
    }

    private final JobRoleService jobRoleService;

    private final StudentService StudentService;
    private final SchoolService schoolService;

    private final GradeService gradeServiceService;
    private final SectionService sectionService;
    private final ScoreService scoreService;
    private final AttendanceService attendanceService;
    private final SubjectService subjectService;


    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody Student student) {
         StudentService.register(student);
        return ResponseEntity.ok("Student registered successfully");

    }

    @PostMapping("/login")
    public String login(@RequestBody Student user) {

        return StudentService.verify(user);
    }


    //Get all Student data
@GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
    //Retrieve all Students
    List<Student> Students=StudentService.findAllStudents();
    return new ResponseEntity<>(Students, HttpStatus.OK);}
    @GetMapping("/scores/{studentId}/{subjectId}")
    public List<Score> getScoresByStudentAndSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return scoreService.getScoresByStudentIdAndSubjectId(studentId, subjectId);
    }
    @GetMapping("/attendance/{studentId}/{subjectId}")
    public List<Attendance> getAttendanceByStudentAndSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return attendanceService.getAttendanceByStudentIdAndSubjectId(studentId, subjectId);
    }
    @GetMapping("/sections/{id}")
    public ResponseEntity<List<Subject>> getAllSubjectsByStudentId(@PathVariable("id")Long id){
    Grade existingGrade=gradeServiceService.findGradeById(StudentService.findStudentById(id).getGrade().getId());
        List<Subject> subjects = subjectService.findAllSubjectsByGradeId(id);
        return new ResponseEntity<>(subjects,HttpStatus.OK);
    }

    //Get Student by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id")Long id){
    //Retrieve Student by ID
        Student Student=StudentService.findStudentById(id);
        return new ResponseEntity<>(Student, HttpStatus.OK);}

    //Add new Student
    @PostMapping("/add")

    public ResponseEntity<Student> addStudent(@RequestBody Student student){

        //Retrieve JobRoleId & add it to Student entity as a foreign key
        if(student.getSchool()!=null&&student.getSchool().getId()!=null){
            School existingJobRole = schoolService.findSchoolById(student.getSchool().getId());
            student.setSchool(existingJobRole);
        }

        if(student.getGrade()!=null&&student.getGrade().getId()!=null){
            Grade existingGrade = gradeServiceService.findGradeById(student.getGrade().getId());
            student.setGrade(existingGrade);
        }

        //Retrieve SchoolId & add it to Student entity as a foreign key
        if(student.getSection()!=null&&student.getSection().getId()!=null){
            Section existingSchool = sectionService.findSectionById(student.getSection().getId());
            student.setSection(existingSchool);
        }


        Student newStudent = StudentService.addStudent(student);



        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);

    }

    //update a Student by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id")Long id){
    Student oldStudent= StudentService.findStudentById(id);

    //Update fields only if they are not null

    if(student.getName()!=null){oldStudent.setName(student.getName());}

    if(student.getUsername()!=null){oldStudent.setUsername(student.getUsername());}

    if(student.getPassword()!=null){oldStudent.setPassword(student.getPassword());}

        //Retrieve SchoolId & add it to Student entity as a foreign key
        if(student.getSection()!=null&&student.getSection().getId()!=null){
            Section existingSection = sectionService.findSectionById(student.getSection().getId());
            oldStudent.setSection(existingSection);
        }

        //Retrieve JobRoleId & add it to Student entity as a foreign key
        if(student.getSchool()!=null&&student.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(student.getSchool().getId());
            oldStudent.setSchool(existingSchool);
        }
        //Retrieve JobRoleId & add it to Student entity as a foreign key
        if(student.getGrade()!=null&&student.getGrade().getId()!=null){
            Grade existingGrade = gradeServiceService.findGradeById(student.getGrade().getId());
            oldStudent.setGrade(existingGrade);
        }


//Check if team ID is not null if true retrieve team by team id & add it as a foreign key to Student

     Student updateStudent = StudentService.updateStudent(oldStudent);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);

    }
//Find Students associated in each Team


    //Delete Student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        StudentService.deleteStudent(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

