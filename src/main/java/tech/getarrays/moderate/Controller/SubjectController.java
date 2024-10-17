package tech.getarrays.moderate.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.moderate.model.Employee;
import tech.getarrays.moderate.model.Grade;
import tech.getarrays.moderate.model.School;
import tech.getarrays.moderate.model.Subject;
import tech.getarrays.moderate.service.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    //Initialise & define service files

    //Constructor
    public SubjectController( EmployeeService employeeService, SubjectService subjectService, SchoolService schoolService, GradeService gradeServiceService) {
        this.employeeService = employeeService;
        this.subjectService = subjectService;
        this.schoolService = schoolService;
        this.gradeServiceService = gradeServiceService;
    }


    private final EmployeeService employeeService;


    private final SubjectService subjectService;
    private final SchoolService schoolService;

    private final GradeService gradeServiceService;


    //Get all Subject data
@GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects(){
    //Retrieve all Subjects
    List<Subject> Subjects=subjectService.findAllSubjects();
    return new ResponseEntity<>(Subjects, HttpStatus.OK);}

    //Get Subject by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id")Long id){
    //Retrieve Subject by ID
        Subject Subject=subjectService.findSubjectById(id);
        return new ResponseEntity<>(Subject, HttpStatus.OK);}

    //Add new Subject
    @PostMapping("/add")

    public ResponseEntity<Subject> addSubject(@RequestBody Subject Subject){

        //Retrieve JobRoleId & add it to Subject entity as a foreign key
        if(Subject.getGrade()!=null&&Subject.getGrade().getId()!=null){
            Grade existingJobRole = gradeServiceService.findGradeById(Subject.getGrade().getId());
            Subject.setGrade(existingJobRole);
        }

        if(Subject.getEmployee()!=null&&Subject.getEmployee().getId()!=null){
            Employee existingEmployee = employeeService.findEmployeeById(Subject.getEmployee().getId());
            Subject.setEmployee(existingEmployee);
        }
        //Retrieve JobRoleId & add it to Subject entity as a foreign key
        if(Subject.getSchool()!=null&&Subject.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(Subject.getSchool().getId());
            Subject.setSchool(existingSchool);
        }


        Subject newSubject = subjectService.addSubject(Subject);



        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);

    }

    //update a Subject by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Subject> updateSubject(@RequestBody Subject Subject,@PathVariable("id")Long id){
    Subject oldSubject= subjectService.findSubjectById(id);

    //Update fields only if they are not null

    if(Subject.getName()!=null){oldSubject.setName(Subject.getName());}



        //Retrieve JobRoleId & add it to Subject entity as a foreign key
        if(Subject.getGrade().getId()!=null){
            Grade existingGrade = gradeServiceService.findGradeById(Subject.getGrade().getId());
            oldSubject.setGrade(existingGrade);
        }
        //Retrieve JobRoleId & add it to Subject entity as a foreign key
        if(Subject.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(Subject.getSchool().getId());
            Subject.setSchool(existingSchool);
        }
        //Retrieve JobRoleId & add it to Subject entity as a foreign key
        if(Subject.getEmployee().getId()!=null){
            Employee existingEmployee = employeeService.findEmployeeById(Subject.getEmployee().getId());
            oldSubject.setEmployee(existingEmployee);
        }

//Check if team ID is not null if true retrieve team by team id & add it as a foreign key to Subject

     Subject updateSubject = subjectService.updateSubject(oldSubject);
        return new ResponseEntity<>(updateSubject, HttpStatus.OK);

    }
//Find Subjects associated in each Team


    //Delete Subject
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id){
        subjectService.deleteSubject(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

