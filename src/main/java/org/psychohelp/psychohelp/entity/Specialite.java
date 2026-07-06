package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class Specialite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nom_specialite",nullable = false, length = 60,unique = true)
    private String nom;
    @OneToMany(mappedBy = "specialite")
    @JsonIgnore
    private List<Psychologue> psychologues;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;


}
