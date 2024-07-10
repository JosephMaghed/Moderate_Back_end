package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.model.Grade;
import tech.getarrays.empoyeemanager.repo.GradeRepo;

import java.util.List;

@Service
public class GradeService {
    //Create a repo property
    private final GradeRepo GradeRepo;
    //Constructor
    @Autowired
    public GradeService(GradeRepo GradeRepo) {
        this.GradeRepo = GradeRepo;
    }


    public Grade addGrade(Grade Grade){
        return  GradeRepo.save(Grade);
    }

    public List<Grade> findAllGrades(){
        return GradeRepo.findAll();
    }

    public Grade updateGrade(Grade Grade){return GradeRepo.save(Grade);}

    public Grade findGradeById(Long id)
    {return GradeRepo.findById(id).orElse(null);}

    public void deleteGrade(Long id){
        GradeRepo.deleteById(id);
    }

}
