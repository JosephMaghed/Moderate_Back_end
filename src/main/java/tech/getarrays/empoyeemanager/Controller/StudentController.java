package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Grade;
import tech.getarrays.empoyeemanager.model.Section;
import tech.getarrays.empoyeemanager.model.Student;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {
    //Initialise & define service files

    //Constructor
    public StudentController(JobRoleService jobRoleService, StudentService StudentService, SchoolService schoolService, GradeService gradeServiceService, SectionService sectionService) {
        this.jobRoleService = jobRoleService;
        this.StudentService = StudentService;
        this.schoolService = schoolService;
        this.gradeServiceService = gradeServiceService;
        this.sectionService = sectionService;
    }

    private final JobRoleService jobRoleService;

    private final StudentService StudentService;
    private final SchoolService schoolService;

    private final GradeService gradeServiceService;
    private final SectionService sectionService;



    //Get all Student data
@GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
    //Retrieve all Students
    List<Student> Students=StudentService.findAllStudents();
    return new ResponseEntity<>(Students, HttpStatus.OK);}

    //Get Student by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id")Long id){
    //Retrieve Student by ID
        Student Student=StudentService.findStudentById(id);
        return new ResponseEntity<>(Student, HttpStatus.OK);}

    //Add new Student
    @PostMapping("/add")

    public ResponseEntity<Student> addStudent(@RequestBody Student Student){

        //Retrieve JobRoleId & add it to Student entity as a foreign key
        if(Student.getSchool()!=null&&Student.getSchool().getId()!=null){
            School existingJobRole = schoolService.findSchoolById(Student.getSchool().getId());
            Student.setSchool(existingJobRole);
        }

        if(Student.getGrade()!=null&&Student.getGrade().getId()!=null){
            Grade existingGrade = gradeServiceService.findGradeById(Student.getGrade().getId());
            Student.setGrade(existingGrade);
        }

        //Retrieve SchoolId & add it to Student entity as a foreign key
        if(Student.getSection()!=null&&Student.getSection().getId()!=null){
            Section existingSchool = sectionService.findSectionById(Student.getSection().getId());
            Student.setSection(existingSchool);
        }


        Student newStudent = StudentService.addStudent(Student);



        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);

    }

    //update a Student by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Student> updateStudent(@RequestBody Student Student,@PathVariable("id")Long id){
    Student oldStudent= StudentService.findStudentById(id);

    //Update fields only if they are not null

    if(Student.getName()!=null){oldStudent.setName(Student.getName());}

    if(Student.getEmail()!=null){oldStudent.setEmail(Student.getEmail());}

    if(Student.getPhone()!=null){oldStudent.setPhone(Student.getPhone());}

        //Retrieve SchoolId & add it to Student entity as a foreign key
        if(Student.getSection()!=null&&Student.getSection().getId()!=null){
            Section existingSchool = sectionService.findSectionById(Student.getSection().getId());
            Student.setSection(existingSchool);
        }

        //Retrieve JobRoleId & add it to Student entity as a foreign key
        if(Student.getSchool().getId()!=null){
            School existingJobRole = schoolService.findSchoolById(Student.getSchool().getId());
            Student.setSchool(existingJobRole);
        }

        //Retrieve SchoolId & add it to Student entity as a foreign key
        if(Student.getSchool()!=null){
            School existingSchool = schoolService.findSchoolById(Student.getSchool().getId());
            Student.setSchool(existingSchool);
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

