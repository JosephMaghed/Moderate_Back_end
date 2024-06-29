package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.School;
import tech.getarrays.empoyeemanager.repo.SchoolRepo;

import java.util.List;
import java.util.UUID;

@Service
public class SchoolService {
    //Create a repo property
    private final SchoolRepo SchoolRepo;
    //Constructor
    @Autowired
    public SchoolService(SchoolRepo SchoolRepo) {
        this.SchoolRepo = SchoolRepo;
    }


    public School addSchool(School School){
        return  SchoolRepo.save(School);
    }

    public List<School> findAllSchools(){
        return SchoolRepo.findAll();
    }

    public School updateSchool(School School){return SchoolRepo.save(School);}

    public School findSchoolById(Long id)
    {return SchoolRepo.findById(id).orElse(null);}

    public void deleteSchool(Long id){
        SchoolRepo.deleteById(id);
    }

}
