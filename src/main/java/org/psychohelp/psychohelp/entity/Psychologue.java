package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "psychologues")
@AllArgsConstructor
@NoArgsConstructor
public class Psychologue extends Utilisateur{

    private Boolean status;
    private String description;
    private String diplome_path;
    private String  cv_path;
    private Boolean etat;

    @ManyToOne
    @JoinColumn(name="specialite_id")
    private Specialite specialite;

    @OneToMany(mappedBy = "psychologue")
    private List<Creneau> creneaux;


}
