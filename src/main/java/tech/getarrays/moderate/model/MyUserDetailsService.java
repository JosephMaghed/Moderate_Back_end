package tech.getarrays.moderate.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.repo.StudentRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        
        return new UserPrincipal(user);
    }
}
