package org.psychohelp.psychohelp.dto;

import lombok.Data;
import org.psychohelp.psychohelp.enumeration.TypeNotificationEnum;

import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO {

    private int id;
    private String titre;
    private String message;
    private TypeNotificationEnum type;
    private Boolean lu;
    private LocalDateTime dateCreation;
}
