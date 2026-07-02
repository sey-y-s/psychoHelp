package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories_test")
@Data
public class CategorieTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_categorie", nullable = false, unique = true, length = 100)
    private String nomCategorie;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "categorieTest",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Test> tests = new ArrayList<>();
}