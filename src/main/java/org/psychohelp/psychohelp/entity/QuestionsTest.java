package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Test test;


    @OneToMany(mappedBy = "questionTest",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ChoixMultiple> choixMultiples;

}
