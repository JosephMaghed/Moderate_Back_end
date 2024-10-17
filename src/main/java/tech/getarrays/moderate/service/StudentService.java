package tech.getarrays.moderate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;
    @Autowired
    public StudentService(StudentRepo StudentRepo) {
        this.StudentRepo = StudentRepo;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Student register(Student user) {
        user.setPassword(encoder.encode(user.getPassword()));
        StudentRepo.save(user);
        return user;
    }

    public String verify(Student user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
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
