package tech.getarrays.moderate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class JobRole {

    @Id //Primary Key
    @SequenceGenerator(name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="employee_sequence")//How to generate value
    private Long id;

    private String jobname;

    private String imageUrl;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private School school;

}
