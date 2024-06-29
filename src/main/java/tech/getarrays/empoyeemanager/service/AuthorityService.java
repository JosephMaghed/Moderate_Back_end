package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.Authority;
import tech.getarrays.empoyeemanager.repo.TeamRepo;

import java.util.List;

@Service
public class AuthorityService {
    private  final TeamRepo teamRepo;

    //Constructor
    @Autowired
    public AuthorityService(TeamRepo teamRepo) {
        this.teamRepo=teamRepo;
    }

    public Authority addAuthority(Authority authority){
        return  teamRepo.save(authority);
    }

    public List<Authority> findAllAuthorities(){
        return teamRepo.findAll();
    }

    public Authority findAuthorityById(Long id){
        return teamRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not fond"));}

    public Authority updateAuthority(Authority authority){
        return  teamRepo.save(authority);
    }

    public void deleteAuthority(Long id){
         teamRepo.deleteById(id);
    }
}
