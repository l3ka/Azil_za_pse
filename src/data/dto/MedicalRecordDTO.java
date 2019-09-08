package data.dto;

import java.sql.Date;

public class MedicalRecordDTO {

    private Integer medicalRecordId;
    private Date creationDate;

    public MedicalRecordDTO() {}

    public MedicalRecordDTO(Integer medicalRecordId, Date creationDate) {
        this.medicalRecordId = medicalRecordId;
        this.creationDate = creationDate;
    }

    public int getID() {
        return medicalRecordId;
    }

    public void setID(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
