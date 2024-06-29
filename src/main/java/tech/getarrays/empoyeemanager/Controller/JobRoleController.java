package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Authority;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.JobRole;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.AuthorityService;
import tech.getarrays.empoyeemanager.service.EmployeeService;
import tech.getarrays.empoyeemanager.service.JobRoleService;
import tech.getarrays.empoyeemanager.service.SchoolService;

import java.util.List;

@RestController
@RequestMapping("/jobrole")
public class JobRoleController {
    public JobRoleController (AuthorityService authorityService, JobRoleService jobRoleService, EmployeeService employeeService, SchoolService schoolService) {
        this.jobRoleService = jobRoleService;
        this.schoolService = schoolService;
    }
    private final JobRoleService jobRoleService;
    private final SchoolService schoolService;




    @PostMapping("/add")
    public ResponseEntity<JobRole> addJobRole(@RequestBody JobRole jobRole){
        JobRole newJobRole;
        newJobRole = jobRoleService.addJobRole(jobRole);

        //Retrieve JobRoleId & add it to employee entity as a foreign key
        if(jobRole.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(jobRole.getSchool().getId());
            jobRole.setSchool(existingSchool);
        }
        return new ResponseEntity<>(newJobRole, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<JobRole>> getAllJobRoles(){
        List<JobRole> jobRoles = jobRoleService.findAllJobRoles();
        return new ResponseEntity<>(jobRoles,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <JobRole> getJobRoleById(@PathVariable("id")Long id){
        JobRole jobRoles= jobRoleService.findJobRoleById(id);
        return new ResponseEntity<>(jobRoles,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")

    public ResponseEntity<JobRole> updateJobRole(@RequestBody JobRole jobRole, @PathVariable("id")Long id){
        JobRole existingJobRole = jobRoleService.findJobRoleById(id);

        if(jobRole.getJobname()!=null) {
            existingJobRole.setJobname(jobRole.getJobname());
        }
        if(jobRole.getImageUrl()!=null) {
            existingJobRole.setImageUrl(jobRole.getImageUrl());
        }

        //Retrieve JobRoleId & add it to employee entity as a foreign key
        if(jobRole.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(jobRole.getSchool().getId());
            jobRole.setSchool(existingSchool);
        }




        JobRole updateJobRole = jobRoleService.updateJobRole(existingJobRole);
        return new ResponseEntity<>(updateJobRole, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable("id")Long id){
        jobRoleService.deleteJobRole(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
