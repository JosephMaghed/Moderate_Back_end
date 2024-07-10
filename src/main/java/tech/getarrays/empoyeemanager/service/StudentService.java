package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.model.Student;
import tech.getarrays.empoyeemanager.repo.StudentRepo;

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

    public Student updateStudent(Student Student){return StudentRepo.save(Student);}

    public Student findStudentById(Long id)
    {return StudentRepo.findById(id).orElse(null);}

    public void deleteStudent(Long id){
        StudentRepo.deleteById(id);
    }

}
