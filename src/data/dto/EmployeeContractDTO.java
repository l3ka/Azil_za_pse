package data.dto;

import java.sql.Date;

public class EmployeeContractDTO {

    private Date from;
    private EmployeeDTO employee;
    private EmploymentContractDTO employmentContract;
    private Date to;

    public EmployeeContractDTO() {}

    public EmployeeContractDTO(Date from, EmployeeDTO employee, EmploymentContractDTO employmentContract, Date to) {
        this.from = from;
        this.employee = employee;
        this.employmentContract = employmentContract;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public EmploymentContractDTO getEmploymentContract() {
        return employmentContract;
    }

    public void setEmploymentContract(EmploymentContractDTO employmentContract) {
        this.employmentContract = employmentContract;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

}
