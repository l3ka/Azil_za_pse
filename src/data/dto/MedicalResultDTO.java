package data.dto;

//Nalaz
public class MedicalResultDTO {
    private String resultsAndOpinion;
    private String therapy;

    public MedicalResultDTO(){
    }

    public MedicalResultDTO(String resultsAndOpinion, String therapy) {
        this.resultsAndOpinion = resultsAndOpinion;
        this.therapy = therapy;
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