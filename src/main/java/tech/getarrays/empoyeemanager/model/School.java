package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class School implements Serializable {
    @Id //Primary Key
    @SequenceGenerator(name="employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator="employee_sequence")//How to generate value
    private Long id;

    private String name;

}
