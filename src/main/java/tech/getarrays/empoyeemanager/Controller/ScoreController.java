package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.*;
import tech.getarrays.empoyeemanager.service.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    //Initialise & define service files
    public ScoreController(ScoreService scoreService, ScoreService ScoreService, StudentService studentService, SubjectService subjectService, SectionService sectionService, SchoolService schoolService) {
        this.ScoreService = ScoreService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.sectionService = sectionService;
        this.schoolService = schoolService;
    }
    private final ScoreService ScoreService;

    private final StudentService studentService;
    private final SubjectService subjectService;
    private final SectionService sectionService;


    private final SchoolService schoolService;



    @PostMapping("/add")
    public ResponseEntity<Score> addScore(@RequestBody Score Score){
        Score newScore;

        //Retrieve SchoolId & add it to Score entity as a foreign key
        if(Score.getSchool()!=null&&Score.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(Score.getSchool().getId());
            Score.setSchool(existingSchool);
        }

        //Retrieve StudentId & add it to Score entity as a foreign key
        if(Score.getSubject()!=null&&Score.getSubject().getId()!=null){
            Student existingStudent = studentService.findStudentById(Score.getStudent().getId());
            Score.setStudent(existingStudent);
        }
        //Retrieve StudentId & add it to Score entity as a foreign key
        if(Score.getSection()!=null&&Score.getSection().getId()!=null){
            Section existingSection = sectionService.findSectionById(Score.getSection().getId());
            Score.setSection(existingSection);
        }
        //Retrieve StudentId & add it to Score entity as a foreign key
        if(Score.getStudent()!=null&&Score.getStudent().getId()!=null){
            Student existingSubject = studentService.findStudentById(Score.getStudent().getId());
            Score.setStudent(existingSubject);
        }


        newScore = ScoreService.addScore(Score);
        return new ResponseEntity<>(newScore, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Score>> getAllAuthorities(){
        List<Score> authorities = ScoreService.findAllAuthorities();
        return new ResponseEntity<>(authorities,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <Score> getScoreById(@PathVariable("id")Long id){
       Score teams= ScoreService.findScoreById(id);
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Score> updateScore(@RequestBody Score Score, @PathVariable("id")Long id){
        Score existingScore = ScoreService.findScoreById(id);


        //Check if parameters are null if not update accordingly
        if(Score.getSubject()!=null&&Score.getSubject().getId()!=null){
            Subject existingSubject = subjectService.findSubjectById(Score.getSubject().getId());
            existingScore.setSubject(existingSubject);
        }
        if(Score.getSchool()!=null&&Score.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(Score.getSchool().getId());
            existingScore.setSchool(existingSchool);
        }
        if(Score.getStudent()!=null&&Score.getSchool().getId()!=null){
            Student existingStudent = studentService.findStudentById(Score.getStudent().getId());
            existingScore.setStudent(existingStudent);
        }
        if(Score.getStudentScore()!=null){
            existingScore.setStudentScore(Score.getStudentScore());
        }
        if(Score.getFullMark()!=null){
            existingScore.setFullMark(Score.getFullMark());
        }

        if(Score.getScoreWeightPercentage()!=null){
            existingScore.setScoreWeightPercentage(Score.getScoreWeightPercentage());
        }
        if(Score.getName()!=null){
            existingScore.setName(Score.getName());
        }
        if(Score.getDate()!=null){
            existingScore.setDate(Score.getDate());
        }

        Score updateScore = ScoreService.updateScore(existingScore);
        return new ResponseEntity<>(updateScore, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteScore(@PathVariable("id")Long id){
       ScoreService.deleteScore(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
