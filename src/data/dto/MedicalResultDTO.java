package data.dto;

//Nalaz
public class MedicalResultDTO {
    private Integer medicalResultId;
    private String resultsAndOpinion;
    private String therapy;

    public MedicalResultDTO() {
    }

    public MedicalResultDTO(Integer medicalResultId, String resultsAndOpinion, String therapy) {
        this.medicalResultId = medicalResultId;
        this.resultsAndOpinion = resultsAndOpinion;
        this.therapy = therapy;
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

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }
}
