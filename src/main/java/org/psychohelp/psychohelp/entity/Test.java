package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name ="tests" )
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @Column(nullable = false, length = 30)
    private String nom_test ;
    private String description ;
    private Boolean etat ;


    @OneToMany(mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    @JsonManagedReference // pour sérialiser les questions  quand on démande un test
    private List<QuestionsTest> questions ;

    @ManyToOne
    @JoinColumn(name = "categorie_test_id")
    private CategorieTest categorieTest ;
}
