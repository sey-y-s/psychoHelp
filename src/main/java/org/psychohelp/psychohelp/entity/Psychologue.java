package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "psychologues")
@AllArgsConstructor
@NoArgsConstructor
public class Psychologue extends Utilisateur{
    private Boolean status=false;
    private String description;
    private String diplome_path;
    private String  cv_path;
    private Boolean etat=true;
    @ManyToOne
    @JoinColumn(name="specialite_id",nullable = false)
    private Specialite specialite;

    @OneToMany(mappedBy = "psychologue")
    private List<Creneau> creneaux;

    @OneToMany(mappedBy = "psychologue")
    private List<Conseil> conseils;

}
