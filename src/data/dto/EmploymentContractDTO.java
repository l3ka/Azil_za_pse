package data.dto;

import java.sql.Date;

public class EmploymentContractDTO {

    private Integer employmentContractId;
    private int active;
    private String position;
    private Date signingDate;
    private Date validationDate;
    private double salary;

    public EmploymentContractDTO() {}

    public EmploymentContractDTO(Integer employmentContractId, int active, String position, Date signingDate, Date validationDate, double salary) {
        this.employmentContractId = employmentContractId;
        this.active = active;
        this.position = position;
        this.signingDate = signingDate;
        this.validationDate = validationDate;
        this.salary = salary;
    }

    public Integer getEmploymentContractId() {
        return employmentContractId;
    }

    public void setEmploymentContractId(int employmentContractId) {
        this.employmentContractId = employmentContractId;
    }

    public int getActive() {return this.active; }

    public void setActive(int active) { this.active = active; }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
