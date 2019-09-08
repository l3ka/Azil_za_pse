package data.dto;

public class DrugStoreDTO {

    private Integer drugStoreId;
    private EmployeeDTO employee;
    private int capacity;

    public DrugStoreDTO() {}

    public DrugStoreDTO(Integer drugStoreId,EmployeeDTO employee, int capacity) {
        this.drugStoreId=drugStoreId;
        this.employee = employee;
        this.capacity = capacity;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getDrugStoreId() {
        return drugStoreId;
    }

    public void setDrugStoreId(int drugStoreId) {
        this.drugStoreId = drugStoreId;
    }

}
