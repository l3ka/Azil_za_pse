package data.dto;

import java.sql.Date;

public class EmployeeContractDTO {

    private Date from;
    private String jmbEmployee;
    private int idEmploymentContract;
    private Date to;

    public EmployeeContractDTO() {}

    public EmployeeContractDTO(Date from, String jmbEmployee, int idEmploymentContract, Date to) {
        this.from = from;
        this.jmbEmployee = jmbEmployee;
        this.idEmploymentContract = idEmploymentContract;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public String getJmbEmployee() {
        return jmbEmployee;
    }

    public void setJmbEmployee(String jmbEmployee) {
        this.jmbEmployee = jmbEmployee;
    }

    public int getIdEmploymentContract() {
        return idEmploymentContract;
    }

    public void setIdEmploymentContract(int idEmploymentContract) {
        this.idEmploymentContract = idEmploymentContract;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

}
