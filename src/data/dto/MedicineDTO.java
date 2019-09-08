package data.dto;

public class MedicineDTO {

    private Integer medicineId;
    private String name;
    private String description;
    private Integer quantity;

    public MedicineDTO() {}

    public MedicineDTO(Integer medicineId, String name, String description, Integer quantity) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
