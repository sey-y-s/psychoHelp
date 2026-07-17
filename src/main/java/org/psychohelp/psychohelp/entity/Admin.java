package org.psychohelp.psychohelp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
public class Admin extends Utilisateur{
    @OneToMany(mappedBy = "admin")
    List<Specialite> specialites;

}
