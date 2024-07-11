package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.Score;
import tech.getarrays.empoyeemanager.repo.ScoreRepo;

import java.util.List;

@Service
public class ScoreService {
    private  final ScoreRepo ScoreRepoRepo;

    //Constructor
    @Autowired
    public ScoreService(ScoreRepo ScoreRepoRepo) {
        this.ScoreRepoRepo = ScoreRepoRepo;
    }

    public Score addScore(Score Score){
        return  ScoreRepoRepo.save(Score);
    }

    public List<Score> findAllAuthorities(){
        return ScoreRepoRepo.findAll();
    }

    public Score findScoreById(Long id){
        return ScoreRepoRepo.findById(id).orElse(null);}

    public Score updateScore(Score Score){
        return  ScoreRepoRepo.save(Score);
    }

    public void deleteScore(Long id){
        ScoreRepoRepo.deleteById(id);
    }
}
