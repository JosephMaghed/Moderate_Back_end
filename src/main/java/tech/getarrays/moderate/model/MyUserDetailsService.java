package tech.getarrays.moderate.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.repo.EmployeeRepo;
import tech.getarrays.moderate.repo.StudentRepo;
import tech.getarrays.moderate.service.AuthorityService;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private AuthorityService authorityService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student studentUser = studentRepo.findByUsername(username);
        Employee employeeUser = employeeRepo.findByUsername(username);

        if (studentUser != null) {
            return new StudentPrincipal(studentUser);

    }
        else if(employeeUser != null) {
            return new EmployeePrincipal(employeeUser,authorityService);


        }
        else {
        System.out.println("User Not Found");
        throw new UsernameNotFoundException("user not found");    }
}

    }
