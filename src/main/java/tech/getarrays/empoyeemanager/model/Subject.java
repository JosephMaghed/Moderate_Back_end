package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Subject {  @Id //Primary Key
@SequenceGenerator(name="employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1)
@GeneratedValue(strategy= GenerationType.SEQUENCE,
        generator="employee_sequence")//How to generate value
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Grade grade;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Employee employee;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private School school;
}
