package org.psychohelp.psychohelp.mapper;

import org.mapstruct.Mapper;
import org.psychohelp.psychohelp.dto.NotificationResponseDTO;
import org.psychohelp.psychohelp.entity.Notification;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResponseDTO toDTO(Notification notification);

    List<NotificationResponseDTO> toDTO(List<Notification> notifications);
}
