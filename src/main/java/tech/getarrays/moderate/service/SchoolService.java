package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.model.School;
import tech.getarrays.moderate.repo.SchoolRepo;

import java.util.List;

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
