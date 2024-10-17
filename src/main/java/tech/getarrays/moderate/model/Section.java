package tech.getarrays.moderate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Section {
    @Id //Primary Key
    @SequenceGenerator(name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="employee_sequence")//How to generate value
    private Long id;

    @NotBlank(message = "Name can not be empty")
    private String name;



    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private School school;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private Grade grade;
}
