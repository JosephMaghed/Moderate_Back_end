package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
    @Column(nullable = false,updatable = false)
    private Long id;

    @NotBlank(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must e in the correct format")
    private String email;

    @NotBlank(message = "Password cannot be blank ")
    @Size(min = 8, message = "Please enter a password of min 8 characters ")
    private String phone;


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private School school;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private Grade grade;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)

    private Section Section;
}
