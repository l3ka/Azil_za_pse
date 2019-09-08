package data.dto;

import java.sql.Date;

public class MedicalResultDTO {

    private Integer medicalResultId;
    private String resultsAndOpinion;
    private Date date;
    private Integer dogId;
    private String veterinarianJMB;

    public MedicalResultDTO() {}

    public MedicalResultDTO(Integer medicalResultId, String resultsAndOpinion, Date date, Integer dogId, String veterinarianJMB) {
        this.medicalResultId = medicalResultId;
        this.resultsAndOpinion = resultsAndOpinion;
        this.date = date;
        this.dogId = dogId;
        this.veterinarianJMB = veterinarianJMB;
    }

    public Integer getMedicalResultId() {
        return medicalResultId;
    }

    public void setMedicalResultId(int medicalResultId) {
        this.medicalResultId = medicalResultId;
    }

    public String getResultsAndOpinion() {
        return resultsAndOpinion;
    }

    public void setResultsAndOpinion(String resultsAndOpinion) {
        this.resultsAndOpinion = resultsAndOpinion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDogId() { return dogId;}

    public void setDogId(Integer dogId) { this.dogId = dogId; }

    public String getVeterinarianJMB() { return  this.veterinarianJMB; }

    public void setVeterinarianJMB(String veterinarianJMB) { this.veterinarianJMB = veterinarianJMB; }

}
