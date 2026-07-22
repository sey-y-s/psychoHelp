package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  TestReponseDTO {
        private int id ;
        private String nom_test;
        private String nomCategorie;

}
