package org.psychohelp.psychohelp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPsyDto {

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères.")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ\\s'-]+$",
            message = "Le nom ne doit contenir que des lettres."
    )
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères.")
    @Pattern(
            regexp = "^[A-Za-zÀ-ÿ\\s'-]+$",
            message = "Le prénom ne doit contenir que des lettres."
    )
    private String prenom;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
            regexp = "^[0-9]{8,15}$",
            message = "Le numéro de téléphone est invalide."
    )
    private String telephone;

    @NotBlank(message = "L'adresse email est obligatoire.")
    @Email(message = "Adresse email invalide.")
    private String mail;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    private  String motDePasse;

    //private LocalDate dateCreation;

    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
    private String description;

    //private String diplome_path;
    //private String  cv_path;

    @NotNull(message = "Veuillez choisir une spécialité.")
    private Integer idSpecialite;
}
