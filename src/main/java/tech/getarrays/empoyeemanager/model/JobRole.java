package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotBlank(message = "Name can not be empty")
    private String Jobname;

    private String imageUrl;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private School school;

}
