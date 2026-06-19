package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "questiontest")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id ;
    private String question ;
}
