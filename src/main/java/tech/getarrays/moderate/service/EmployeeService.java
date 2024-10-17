package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.model.Employee;
import tech.getarrays.moderate.repo.EmployeeRepo;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    //Create a repo property
    private final EmployeeRepo employeeRepo;
    //Constructor
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());

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
