package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor @NoArgsConstructor @Data
public class ConseilDtoForPyschologue {
    private int id;
    private String titre;
    private String description;
    private StatusConseilEnum statusConseil;
    private LocalDate datePublication;
}
