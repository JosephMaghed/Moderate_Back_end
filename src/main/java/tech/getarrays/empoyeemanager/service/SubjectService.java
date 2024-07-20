package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.model.Subject;
import tech.getarrays.empoyeemanager.repo.SubjectRepo;

import java.util.List;

@Service
public class SubjectService {
    //Create a repo property
    private final SubjectRepo SubjectRepo;
    //Constructor
    @Autowired
    public SubjectService(SubjectRepo SubjectRepo) {
        this.SubjectRepo = SubjectRepo;
    }


    public Subject addSubject(Subject Subject){
        return  SubjectRepo.save(Subject);
    }

    public List<Subject> findAllSubjects(){
        return SubjectRepo.findAll();
    }

    public List<Subject> findAllSubjectsBySchoolId(Long Id){
        return SubjectRepo.findSubjectBySchoolId(Id);
    }


    public Subject updateSubject(Subject Subject){return SubjectRepo.save(Subject);}

    public Subject findSubjectById(Long id)
    {return SubjectRepo.findById(id).orElse(null);}

    public void deleteSubject(Long id){
        SubjectRepo.deleteById(id);
    }

}
