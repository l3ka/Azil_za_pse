package data.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class TakingMedicineDTO {

    private Timestamp date;
    private VeterinarianDTO veterinarian;
    private MedicineDTO medicine;
    private MedicalResultDTO medicalResult;
    private Integer quantity;

    public TakingMedicineDTO(Timestamp date, VeterinarianDTO veterinarian, MedicineDTO medicine, MedicalResultDTO medicalResult, Integer quantity) {
        this.date = date;
        this.veterinarian = veterinarian;
        this.medicine = medicine;
        this.medicalResult = medicalResult;
        this.quantity = quantity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public VeterinarianDTO getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(VeterinarianDTO veterinarian) {
        this.veterinarian = veterinarian;
    }

    public MedicineDTO getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineDTO medicine) {
        this.medicine = medicine;
    }

    public MedicalResultDTO getMedicalResult() {
        return medicalResult;
    }

    public void setMedicalResult(MedicalResultDTO medicalResult) {
        this.medicalResult = medicalResult;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakingMedicineDTO that = (TakingMedicineDTO) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(veterinarian, that.veterinarian) &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(medicalResult, that.medicalResult) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, veterinarian, medicine, medicalResult, quantity);
    }

}
