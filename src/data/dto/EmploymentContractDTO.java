package data.dto;

import java.util.Date;

public class EmploymentContractDTO {
    private String position;
    private Date signingDate;
    private int validationDate;
    private int salary;

    public EmploymentContractDTO() {
    }

    public EmploymentContractDTO(String position, Date signingDate, int validationDate, int salary) {
        this.position = position;
        this.signingDate = signingDate;
        this.validationDate = validationDate;
        this.salary = salary;
    }

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

    public int getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(int validationDate) {
        this.validationDate = validationDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}