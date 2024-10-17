package tech.getarrays.moderate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

//Use @Entity to make sure that this class gets mapped to any database
@Entity
@Data
public class Score implements Serializable {  @Id //Primary Key
@SequenceGenerator(name="employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1)
@GeneratedValue(strategy= GenerationType.SEQUENCE,
        generator="employee_sequence")//How to generate value
    private Long id;

    private Date date;
    private  Long studentScore;
    @Positive(message = "fullMark must be greater than zero")

    private  Long fullMark;
    private  Long ScoreWeightPercentage;
    private  String name;





    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "SchoolId")  // Name of the foreign key column

    School school;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectId")  // Name of the foreign key column

    Subject subject;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "sectionId")  // Name of the foreign key column

    Section section;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")  // Name of the foreign key column

    Student student;




}
