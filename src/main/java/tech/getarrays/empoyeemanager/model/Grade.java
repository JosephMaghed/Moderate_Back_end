package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Grade {
    @Id //Primary Key
    @SequenceGenerator(name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="employee_sequence")//How to generate value
    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "order can not be empty")
    private Long level;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private School school;

}
