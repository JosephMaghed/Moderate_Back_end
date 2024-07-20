package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.*;
import tech.getarrays.empoyeemanager.service.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    //Initialise & define service files

    public SchoolController(SchoolService schoolService, StudentService studentService, EmployeeService employeeService, SectionService sectionService, GradeService gradeService, SubjectService subjectService, JobRoleService jobRoleService) {
        this.schoolService = schoolService;
        this.studentService = studentService;
        this.employeeService = employeeService;
        this.sectionService = sectionService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.jobRoleService = jobRoleService;
    }
    private final SchoolService schoolService;
    private final StudentService studentService;

    private final EmployeeService employeeService;

    private final SectionService sectionService;

    private final GradeService gradeService;

    private final SubjectService subjectService;
    private final JobRoleService jobRoleService;






    @PostMapping("/add")
    public ResponseEntity<School> addSchool(@RequestBody School school){
        School newschool;
        //Retrieve TeamId & add it to employee entity as a foreign key
       
        newschool = schoolService.addSchool(school);
        return new ResponseEntity<>(newschool, HttpStatus.CREATED);

    }

    //Association functions
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable("id")Long id){
        List<Employee> employees = employeeService.findAllEmployeesBySchoolId(id);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
    @GetMapping("/jobroles/{id}")
    public ResponseEntity<List<JobRole>> getAllJobRolesBySchoolId(@PathVariable("id")Long id){
        List<JobRole> jobRoles = jobRoleService.findAllJobRolesBySchoolId(id);
        return new ResponseEntity<>(jobRoles,HttpStatus.OK);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<List<Student>> getAllStudents(@PathVariable("id")Long id){
        List<Student> students = studentService.findAllStudentsBySchoolId(id);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
    @GetMapping("/sections/{id}")
    public ResponseEntity<List<Section>> getAllSections(@PathVariable("id")Long id){
        List<Section> Sections = sectionService.findAllSectionsBySchoolId(id);
        return new ResponseEntity<>(Sections,HttpStatus.OK);
    }
    @GetMapping("/grades/{id}")
    public ResponseEntity<List<Grade>> getAllGradesBySchoolId(@PathVariable("id")Long id){
        List<Grade> grades = gradeService.findAllGradesBySchoolId(id);
        return new ResponseEntity<>(grades,HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<List<Subject>> getAllSubjectsBySchoolId(@PathVariable("id")Long id){
        List<Subject> subjects = subjectService.findAllSubjectsBySchoolId(id);
        return new ResponseEntity<>(subjects,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<School>> getAllSchools(){
        List<School> schools = schoolService.findAllSchools();
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <School> getSchoolById(@PathVariable("id")Long id){
       School schools= schoolService.findSchoolById(id);
        return new ResponseEntity<>(schools,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<School> updateSchool(@RequestBody School school, @PathVariable("id")Long id){
        School existingschool = schoolService.findSchoolById(id);

        //Check if parameters are null if not update accordingly


        if(school.getName()!=null) {
            existingschool.setName(school.getName());
        }




        School updateschool = schoolService.updateSchool(existingschool);
        return new ResponseEntity<>(updateschool, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSchool(@PathVariable("id")Long id){
       schoolService.deleteSchool(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
