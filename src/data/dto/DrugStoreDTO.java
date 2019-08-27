package data.dto;

//Skladiste lijekova
public class DrugStoreDTO {
    private EmployeeDTO employee;
    private int capacity;

    public DrugStoreDTO() {
    }

    public DrugStoreDTO(EmployeeDTO employee, int capacity) {
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
}
