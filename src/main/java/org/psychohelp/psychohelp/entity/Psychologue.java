package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatusValidationPsy;

import java.util.List;

@Entity
@Data
@Table(name = "psychologues")
@AllArgsConstructor
@NoArgsConstructor
public class Psychologue extends Utilisateur{
    private StatusValidationPsy status=StatusValidationPsy.ENATTENTE;
    private String description;
    private String diplome_path;
    private String  cv_path;
    private Boolean etat=false;
    @ManyToOne
    @JoinColumn(name="specialite_id",nullable = false)
    private Specialite specialite;

    @OneToMany(mappedBy = "psychologue")
    private List<Creneau> creneaux;

    @OneToMany(mappedBy = "psychologue")
    private List<Conseil> conseils;
}
