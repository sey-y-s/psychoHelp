package org.psychohelp.psychohelp.mapper;

import jdk.jfr.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.entity.Creneau;
import org.springframework.stereotype.Component;

@Component
public class    CreneauMapper {

    @Mapping(
            target = "nomPsychologue",
            expression = "java(creneau.getPsychologue() != null ? creneau.getPsychologue().getNom() + \" \" + creneau.getPsychologue().getPrenom() : null)"
    )
    public CreneauResponseDTO toDTO(Creneau creneau) {
        return null;
    }

    public Creneau toEntity(CreneauDTO dto) {
        return null;
    }
}
