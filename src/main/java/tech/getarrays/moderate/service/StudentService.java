package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.moderate.model.Student;
import tech.getarrays.moderate.model.StudentAttendanceDTO;
import tech.getarrays.moderate.model.StudentScoreDTO;
import tech.getarrays.moderate.repo.StudentRepo;

import java.util.List;

@Service
public class StudentService {
    //Create a repo property
    private final StudentRepo StudentRepo;
    //Constructor
    @Autowired
    public StudentService(StudentRepo StudentRepo) {
        this.StudentRepo = StudentRepo;
    }


    public Student addStudent(Student Student){
        return  StudentRepo.save(Student);
    }

    public List<Student> findAllStudents(){
        return StudentRepo.findAll();
    }
    public List<Student> findAllStudentsBySchoolId(Long SchoolId){return StudentRepo.findStudentBySchoolId(SchoolId);}
    public List<Student> findAllStudentsByGradeId(Long GradeId){
        return StudentRepo.findStudentByGradeId(GradeId);
    }
    public List<Student> findAllStudentsBySectionId(Long GradeId){
        return StudentRepo.findStudentBySectionId(GradeId);
    }

    public List<StudentAttendanceDTO> findAllStudentsAttendance(Long StudentId){
        return StudentRepo.findStudentAttendanceById(StudentId);
    }
    public List<StudentScoreDTO> findAllStudentsScores(Long StudentId){
        return StudentRepo.findStudentScoreById(StudentId);
    }




    public Student updateStudent(Student Student){return StudentRepo.save(Student);}

    public Student findStudentById(Long id)
    {return StudentRepo.findById(id).orElse(null);}

    public void deleteStudent(Long id){
        StudentRepo.deleteById(id);
    }

}
