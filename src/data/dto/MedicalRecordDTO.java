package data.dto;

import java.util.Date;

//Karton
public class MedicalRecordDTO {
    private int ID;
    private Date creationDate;

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(int ID, Date creationDate) {
        this.ID = ID;
        this.creationDate = creationDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}