package tech.getarrays.empoyeemanager.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.model.JobRole;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.service.EmployeeService;
import tech.getarrays.empoyeemanager.service.JobRoleService;
import tech.getarrays.empoyeemanager.service.SchoolService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    //Initialise & define service files

    //Constructor
    public EmployeeController(JobRoleService jobRoleService, EmployeeService employeeService, SchoolService schoolService) {
        this.jobRoleService = jobRoleService;
        this.employeeService = employeeService;
        this.schoolService = schoolService;
    }

    private final JobRoleService jobRoleService;

    private final EmployeeService employeeService;
    private final SchoolService schoolService;

    //Get all employee data
@GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
    //Retrieve all employees
    List<Employee> employees=employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);}

    //Get employee by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Long id){
    //Retrieve employee by ID
        Employee employee=employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);}

    //Add new employee
    @PostMapping("/add")

    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){

        //Retrieve JobRoleId & add it to employee entity as a foreign key
        if(employee.getJobRole()!=null&&employee.getJobRole().getId()!=null){
            JobRole existingJobRole = jobRoleService.findJobRoleById(employee.getJobRole().getId());
            employee.setJobRole(existingJobRole);
        }

        //Retrieve SchoolId & add it to employee entity as a foreign key
        if(employee.getSchool()!=null&&employee.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(employee.getSchool().getId());
            employee.setSchool(existingSchool);
        }


        Employee newEmployee = employeeService.addEmployee(employee);



        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);

    }

    //update an employee by id
    @PutMapping("/update/{id}")

    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id")Long id){
    Employee oldEmployee= employeeService.findEmployeeById(id);

    //Update fields only if they are not null

    if(employee.getName()!=null){oldEmployee.setName(employee.getName());}

    if(employee.getEmail()!=null){oldEmployee.setEmail(employee.getEmail());}

    if(employee.getPhone()!=null){oldEmployee.setPhone(employee.getPhone());}


    if(employee.getImageUrl()!=null){oldEmployee.setImageUrl(employee.getImageUrl());}
        //Retrieve JobRoleId & add it to employee entity as a foreign key
        if(employee.getJobRole()!=null&&employee.getJobRole().getId()!=null){
            JobRole existingJobRole = jobRoleService.findJobRoleById(employee.getJobRole().getId());
            oldEmployee.setJobRole(existingJobRole);
        }

        //Retrieve SchoolId & add it to employee entity as a foreign key
        if(employee.getSchool()!=null&&employee.getSchool().getId()!=null){
            School existingSchool = schoolService.findSchoolById(employee.getSchool().getId());
            oldEmployee.setSchool(existingSchool);
        }
//Check if team ID is not null if true retrieve team by team id & add it as a foreign key to employee

     Employee updateEmployee = employeeService.updateEmployee(oldEmployee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);

    }
//Find Employees associated in each Team


    //Delete Employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}

