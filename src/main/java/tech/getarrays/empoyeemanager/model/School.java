package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class School implements Serializable {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
    @Column(nullable = false,updatable = false)
    private Long id;

    private String name;

}
