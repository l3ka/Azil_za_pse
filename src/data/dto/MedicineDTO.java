package data.dto;

import java.util.Date;

public class MedicineDTO {
    private String name;
    private Date productionDate;
    private int expirationDate;
    private String description;
    private int quantity;

    public MedicineDTO() {
    }

    public MedicineDTO(String name, Date productionDate, int expirationDate, String description, int quantity) {
        this.name = name;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}