package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Grade;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.GradeService;
import tech.getarrays.empoyeemanager.service.GradeService;
import tech.getarrays.empoyeemanager.service.SchoolService;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
    //Initialise & define service files

    //Constructor
    public GradeController( SchoolService schoolService, GradeService gradeService) {
        this.schoolService = schoolService;
        this.GradeService = gradeService;
    }


    private final GradeService GradeService;
    private final SchoolService schoolService;



    //Get all Grade data
@GetMapping("/all")
    public ResponseEntity<List<Grade>> getAllGrades(){
    //Retrieve all Grades
    List<Grade> Grades=GradeService.findAllGrades();
    return new ResponseEntity<>(Grades, HttpStatus.OK);}

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
        if(Grade.getOrder()!=null){oldGrade.setOrder(Grade.getOrder());}


        if(Grade.getSchool().getId()!=null){
            School existingSchoolRole = schoolService.findSchoolById(Grade.getSchool().getId());
            Grade.setSchool(existingSchoolRole);
        }

        //Retrieve SchoolId & add it to Grade entity as a foreign key
        if(Grade.getSchool()!=null){
            School existingSchool = schoolService.findSchoolById(Grade.getSchool().getId());
            Grade.setSchool(existingSchool);
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

