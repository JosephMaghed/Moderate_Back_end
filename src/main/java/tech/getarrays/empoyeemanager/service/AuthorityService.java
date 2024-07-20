package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.Authority;
import tech.getarrays.empoyeemanager.repo.AuthorityRepo;

import java.util.List;

@Service
public class AuthorityService {
    private  final AuthorityRepo authorityRepo;

    //Constructor
    @Autowired
    public AuthorityService(AuthorityRepo authorityRepo) {
        this.authorityRepo = authorityRepo;
    }

    public Authority addAuthority(Authority authority){
        return  authorityRepo.save(authority);
    }

    public List<Authority> findAllAuthorities(){
        return authorityRepo.findAll();
    }
    public List<Authority> findAllAuthoritiesBySchoolID(Long id){
        return authorityRepo.findAuthorityBySchoolId(id);
    }
    public List<Authority> findAllAuthoritiesByEmployeeID(Long id){
        return authorityRepo.findAuthorityByEmployeeId(id);
    }

    public Authority findAuthorityById(Long id){
        return authorityRepo.findById(id).orElse(null);}

    public Authority updateAuthority(Authority authority){
        return  authorityRepo.save(authority);
    }

    public void deleteAuthority(Long id){
         authorityRepo.deleteById(id);
    }
}
