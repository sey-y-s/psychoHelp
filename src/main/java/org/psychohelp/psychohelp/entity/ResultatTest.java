package org.psychohelp.psychohelp.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "resultats_test")
@Data
public class ResultatTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false, length = 50)
    private String niveau;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    //Le  résultat appartient à un citoyen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citoyen_id", nullable = false)
    private Citoyen citoyen;

    //Le résultat est lié à un test spécifique
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
}