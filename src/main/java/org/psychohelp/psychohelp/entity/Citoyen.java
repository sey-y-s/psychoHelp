package org.psychohelp.psychohelp.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="citoyens")
@PrimaryKeyJoinColumn(name="utilisateur_id")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)


public class Citoyen extends Utilisateur{


    // Un citoyen peut passer plusieurs tests (et donc avoir plusieurs resultats)
    @OneToMany(mappedBy = "citoyen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultatTest> resultats = new ArrayList<>();


    // Un citoyen peut réserver plusieurs séances de consultation
    @OneToMany(mappedBy = "citoyen", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Seance> seances = new ArrayList<>();


}