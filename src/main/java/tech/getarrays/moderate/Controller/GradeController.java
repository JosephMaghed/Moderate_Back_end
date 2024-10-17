package tech.getarrays.moderate.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.moderate.model.*;
import tech.getarrays.moderate.service.*;
import tech.getarrays.moderate.service.GradeService;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
    //Initialise & define service files

    //Constructor
    public GradeController(SchoolService schoolService, GradeService gradeService, SectionService sectionService, StudentService studentService, SubjectService subjectService) {
        this.schoolService = schoolService;
        this.GradeService = gradeService;
        this.sectionService = sectionService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }


    private final GradeService GradeService;
    private final SchoolService schoolService;
    private final SectionService sectionService;
    private final StudentService studentService;
    private final SubjectService subjectService;







    //Get all Grade data
@GetMapping("/all")
    public ResponseEntity<List<Grade>> getAllGrades(){
    //Retrieve all Grades
    List<Grade> Grades=GradeService.findAllGrades();
    return new ResponseEntity<>(Grades, HttpStatus.OK);}

    @GetMapping("/sections/{id}")
    public ResponseEntity<List<Section>> getAllSectionsByGradeId(@PathVariable("id")Long id){
        List<Section> sections = sectionService.findAllSectionsByGradeId(id);
        return new ResponseEntity<>(sections,HttpStatus.OK);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<List<Student>> getAllStudentsByGradeId(@PathVariable("id")Long id){
        List<Student> students = studentService.findAllStudentsByGradeId(id);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
    @GetMapping("/subjects/{id}")
    public ResponseEntity<List<Subject>> getAllSubjectsByGradeId(@PathVariable("id")Long id){
        List<Subject> subjects = subjectService.findAllSubjectsByGradeId(id);
        return new ResponseEntity<>(subjects,HttpStatus.OK);
    }

    //Get Grade by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable("id")Long id){
    //Retrieve Grade by ID
        Grade Grade=GradeService.findGradeById(id);
        return new ResponseEntity<>(Grade, HttpStatus.OK);}

    //Add new Grade
    @PostMapping("/add")

    public ResponseEntity<Grade> addGrade(@RequestBody Grade Grade){
        //Retrieve SchoolId & add it to Grade entity as a foreign key
        if(Grade.getSchool()!=null&&Grade.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(Grade.getSchool().getId());
            Grade.setSchool(existingSchool);
        }
        
        Grade newGrade = GradeService.addGrade(Grade);
        
        return new ResponseEntity<>(newGrade, HttpStatus.CREATED);

    }

    //update an Grade by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Grade> updateGrade(@RequestBody Grade Grade,@PathVariable("id")Long id){
    Grade oldGrade= GradeService.findGradeById(id);

    //Update fields only if they are not null

    if(Grade.getName()!=null){oldGrade.setName(Grade.getName());}
        if(Grade.getLevel()!=null){oldGrade.setLevel(Grade.getLevel());}


        if(Grade.getSchool().getId()!=null){
            School existingSchoolRole = schoolService.findSchoolById(Grade.getSchool().getId());
            oldGrade.setSchool(existingSchoolRole);
        }


//Check if team ID is not null if true retrieve team by team id & add it as a foreign key to Grade

     Grade updateGrade = GradeService.updateGrade(oldGrade);
        return new ResponseEntity<>(updateGrade, HttpStatus.OK);

    }
//Find Grades associated in each Team


    //Delete Grade
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable("id") Long id){
        GradeService.deleteGrade(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

