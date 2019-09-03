package data.dto;

import java.sql.Date;

public class MedicineDTO {
    private Integer medicineId;
    private String name;
    private String description;

    public MedicineDTO() {
    }

    public MedicineDTO(Integer medicineId, String name, String description) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
