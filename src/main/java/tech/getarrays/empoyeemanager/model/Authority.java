package tech.getarrays.empoyeemanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
public class Authority implements Serializable {
    @Id//primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)//How to generate value
    @Column(nullable = false,updatable = false)
    private Long teamId;


    private String AuthorityName;
    //Connect to Team table
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Employee Employee;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    School school;

}
