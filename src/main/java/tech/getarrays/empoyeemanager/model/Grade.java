package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Grade {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
    @Column(nullable = false,updatable = false)
    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "order can not be empty")
    private Long order;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private School school;

}
