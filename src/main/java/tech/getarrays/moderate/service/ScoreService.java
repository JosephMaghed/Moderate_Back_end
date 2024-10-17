package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.model.Score;
import tech.getarrays.moderate.repo.ScoreRepo;

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
    public List<Score> getScoresByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return ScoreRepoRepo.findScoresByStudentIdAndSubjectId(studentId, subjectId);
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
