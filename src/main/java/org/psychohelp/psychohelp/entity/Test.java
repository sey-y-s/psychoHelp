package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name ="test" )
@NoArgsConstructor
@AllArgsConstructor


public class Test {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id ;
    private String nom ;
    private String description ;
    private boolean etat ;
}
