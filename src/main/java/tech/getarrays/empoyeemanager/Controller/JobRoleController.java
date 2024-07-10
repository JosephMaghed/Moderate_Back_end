package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    //Initialise & define service files

    public JobRoleController (AuthorityService authorityService, JobRoleService jobRoleService, EmployeeService employeeService, SchoolService schoolService) {
        this.jobRoleService = jobRoleService;
        this.schoolService = schoolService;
    }
    private final JobRoleService jobRoleService;
    private final SchoolService schoolService;




    @PostMapping("/add")
    public ResponseEntity<JobRole> addJobRole(@RequestBody JobRole jobRole){
        JobRole newJobRole;

        //Retrieve JobRoleId & add it to employee entity as a foreign key

        if(jobRole.getSchool()!=null){
            if(jobRole.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(jobRole.getSchool().getId());
            jobRole.setSchool(existingSchool);}
        }
        newJobRole = jobRoleService.addJobRole(jobRole);

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
    public ResponseEntity<JobRole> updateJobRole(@RequestBody JobRole jobRole, @PathVariable("id") Long id) {
        // Retrieve the existing JobRole from the service
        JobRole existingJobRole = jobRoleService.findJobRoleById(id);

        // Update fields of existingJobRole based on jobRole received in the request
        //Check if parameters are null if not update accordingly

        if (jobRole.getJobname() != null) {
            existingJobRole.setJobname(jobRole.getJobname());
        }
        if (jobRole.getImageUrl() != null) {
            existingJobRole.setImageUrl(jobRole.getImageUrl());
        }

        // Check if jobRole has a School and the School id is provided
        if (jobRole.getSchool() != null && jobRole.getSchool().getId() != null) {
            Long schoolId = jobRole.getSchool().getId();

            // Retrieve the School entity from the database
            School existingSchool = schoolService.findSchoolById(schoolId);

            existingJobRole.setSchool(existingSchool);

        }

        // Update the JobRole entity in the database
        JobRole updatedJobRole = jobRoleService.updateJobRole(existingJobRole);

        // Return the updated JobRole with a 200 OK response
        return ResponseEntity.ok(updatedJobRole);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteJobRole(@PathVariable("id")Long id){
        jobRoleService.deleteJobRole(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
}
