package org.psychohelp.psychohelp.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResultatTestRequestDTO {
    private Integer citoyenId;
    private Integer testId;
    private List<Integer> choixIds;
}