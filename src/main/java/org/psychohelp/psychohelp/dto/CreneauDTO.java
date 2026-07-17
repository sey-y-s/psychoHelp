package org.psychohelp.psychohelp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "Informations d'un créneau de consultation")
public class CreneauDTO {

    @Schema(
            description = "Jour de disponibilité du créneau",
            example = "Lundi"
    )
    private String jours;

    @Schema(
            description = "Heure de début du créneau",
            example = "09:00"
    )
    private LocalTime heureDebut;

    @Schema(
            description = "Heure de fin du créneau",
            example = "10:00"
    )
    private LocalTime heureFin;

    @Schema(
            description = "Statut du créneau (true = disponible, false = indisponible)",
            example = "true"
    )
    private Boolean statut;
}