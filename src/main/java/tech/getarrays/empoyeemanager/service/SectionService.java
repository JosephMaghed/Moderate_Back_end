package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.model.Section;
import tech.getarrays.empoyeemanager.repo.SectionRepo;

import java.util.List;

@Service
public class SectionService {
    //Create a repo property
    private final SectionRepo SectionRepo;
    //Constructor
    @Autowired
    public SectionService(SectionRepo SectionRepo) {
        this.SectionRepo = SectionRepo;
    }


    public Section addSection(Section Section){
        return  SectionRepo.save(Section);
    }

    public List<Section> findAllSections(){
        return SectionRepo.findAll();
    }

    public Section updateSection(Section Section){return SectionRepo.save(Section);}

    public Section findSectionById(Long id)
    {return SectionRepo.findById(id).orElse(null);}

    public void deleteSection(Long id){
        SectionRepo.deleteById(id);
    }

}
