package org.psychohelp.psychohelp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "conseils")
@Getter
@Setter
public class Conseil {
    @Id
    private int id;

    @Column(length = 40)
    private String titre;



}
