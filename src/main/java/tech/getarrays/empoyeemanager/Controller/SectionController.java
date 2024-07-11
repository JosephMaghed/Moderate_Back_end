package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Grade;
import tech.getarrays.empoyeemanager.model.Section;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.*;
import tech.getarrays.empoyeemanager.service.SectionService;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {
    //Initialise & define service files
    public SectionController(SectionService SectionService, GradeService gradeService, EmployeeService employeeService, SchoolService schoolService, SectionService sectionService) {
        this.gradeService = gradeService;
        this.employeeService = employeeService;
        this.schoolService = schoolService;
        this.sectionService = sectionService;
    }
    private final GradeService gradeService;
    private final EmployeeService employeeService;
    private final SchoolService schoolService;

    private final SectionService sectionService;




    @PostMapping("/add")
    public ResponseEntity<Section> addSection(@RequestBody Section section){
        Section newSection;
        //Retrieve EmployeeId & add it to Section entity as a foreign key
        if(section.getEmployee()!=null&&section.getEmployee().getId()!=null){
            Employee existingEmployee = employeeService.findEmployeeById(section.getEmployee().getId());
            section.setEmployee(existingEmployee);
        }
        //Retrieve SchoolId & add it to Section entity as a foreign key
        if(section.getSchool()!=null&&section.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(section.getSchool().getId());
            section.setSchool(existingSchool);
        }

        //Retrieve SchoolId & add it to Section entity as a foreign key
        if(section.getGrade()!=null&&section.getGrade().getId()!=null){
            Grade existingGrade = gradeService.findGradeById(section.getGrade().getId());
            section.setGrade(existingGrade);
        }
        newSection = sectionService.addSection(section);
        return new ResponseEntity<>(newSection, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Section>> getAllAuthorities(){
        List<Section> authorities = sectionService.findAllSections();
        return new ResponseEntity<>(authorities,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <Section> getSectionById(@PathVariable("id")Long id){
       Section teams= sectionService.findSectionById(id);
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<Section> updateSection(@RequestBody Section section, @PathVariable("id")Long id){
        Section existingSection = sectionService.findSectionById(id);


        //Check if parameters are null if not update accordingly
        if(section.getEmployee()!=null&&section.getEmployee().getId()!=null){
            Employee existingEmployee = employeeService.findEmployeeById(section.getEmployee().getId());
            existingSection.setEmployee(existingEmployee);
        }

        if(section.getGrade()!=null&&section.getGrade().getId()!=null){
            Grade existingGrade = gradeService.findGradeById(section.getGrade().getId());
            existingSection.setGrade(existingGrade);
        }
        if(section.getSchool()!=null&&section.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(section.getSchool().getId());
            existingSection.setSchool(existingSchool);
        }
        if(section.getName()!=null){
            existingSection.setName(section.getName());
        }



        Section updateSection = sectionService.updateSection(existingSection);
        return new ResponseEntity<>(updateSection, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteSection(@PathVariable("id")Long id){
       sectionService.deleteSection(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
