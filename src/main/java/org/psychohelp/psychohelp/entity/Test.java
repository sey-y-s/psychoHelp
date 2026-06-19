package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.util.List;

@Data
@Entity
@Table(name ="tests" )
@NoArgsConstructor
@AllArgsConstructor


public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(nullable = false, length = 30)
    private String nom_test ;
    private String description ;
    private boolean etat ;


    @OneToMany(mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<QuestionTest> questions ;
}
