package org.psychohelp.psychohelp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.entity.Creneau;

@Mapper(componentModel = "spring")
public interface CreneauMapper {

    @Mapping(
            target = "nomPsychologue",
            expression = "java(creneau.getPsychologue() != null ? creneau.getPsychologue().getNom() + \" \" + creneau.getPsychologue().getPrenom() : null)"
    )
    CreneauResponseDTO toDTO(Creneau creneau);

    Creneau toEntity(CreneauDTO dto);
}
