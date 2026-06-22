package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "questiontests")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String question ;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @ToString.Exclude
    @JsonBackReference //
    private Test test;


    @OneToMany(mappedBy = "questionTest",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference // pour sérialiser les choix  quand on démande une question
    private List<ChoixMultiple> choixMultiples;

}
