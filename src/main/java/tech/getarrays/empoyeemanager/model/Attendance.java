package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

//Use @Entity to make sure that this class gets mapped to any database
@Entity
@Data
public class Attendance implements Serializable {
    @Id //Primary Key
    @SequenceGenerator(name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="employee_sequence")//How to generate value
    private Long id;

    private Date date;

    private Boolean attended;


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "SchoolId")  // Name of the foreign key column

    School school;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "SubjectId")  // Name of the foreign key column

    Subject subject;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "SectionId")  // Name of the foreign key column

    Section section;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "StudentId")  // Name of the foreign key column

    Student student;




}
