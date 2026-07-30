package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Column(nullable = false, length = 225)
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
    @OnDelete(action = OnDeleteAction.CASCADE) // Génère le ON DELETE CASCADE en SQL

    @ToString.Exclude
    @JsonBackReference
    private CategorieTest categorieTest ;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    private List<Diagnostic> diagnostics;

}
