package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.model.Employee;
import tech.getarrays.moderate.model.Student;
import tech.getarrays.moderate.repo.EmployeeRepo;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    //Create a repo property
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;
    private final EmployeeRepo employeeRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    //Constructor
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employee register(Employee user) {
        user.setPassword(encoder.encode(user.getPassword()));
        employeeRepo.save(user);
        return user;
    }

    public String verify(Employee user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }


    public Employee addEmployee(Employee employee){
      //  employee.setEmployeeCode(UUID.randomUUID().toString());

        return  employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public List<Employee> findAllEmployeesByJobRoleId(Long Id){
        return employeeRepo.findEmployeeByJobRoleId(Id);
    }


    public List<Employee> findAllEmployeesBySchoolId(Long SchoolId){
        return employeeRepo.findEmployeeBySchoolId(SchoolId);
    }


    public Employee updateEmployee(Employee employee){return employeeRepo.save(employee);}

    public Employee findEmployeeById(Long id)
    {return employeeRepo.findById(id).orElse(null);}

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

}
