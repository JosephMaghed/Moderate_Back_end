package tech.getarrays.moderate.model;

import lombok.Data;

@Data
public class StudentScoreDTO {
    private Long studentId;
    private String studentName;
    private Double weightedScore;


}
