package tech.getarrays.moderate.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.getarrays.moderate.service.AuthorityService;
import tech.getarrays.moderate.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeePrincipal implements UserDetails {

    private Employee employee;
    private AuthorityService authorityService;


    public EmployeePrincipal(Employee employee,AuthorityService authorityService) {
        this.employee = employee;
        this.authorityService=authorityService;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Fetch authorities from the database using employee ID and job role ID
        List<String> employeeAuthorities = authorityService.findAllAuthoritiesNameByEmployeeID(employee.getId());
        System.out.println(employee.getId());
        if(employee.getJobRole()!=null) {
            List<String> jobRoleAuthorities = authorityService.findAllAuthoritiesNameByJobRoleID(employee.getJobRole().getId());
            jobRoleAuthorities.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth)));

        }
        // Combine the results into a single list and map them to SimpleGrantedAuthority objects
        employeeAuthorities.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth)));

        return authorities;
    }


    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
