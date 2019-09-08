package data.dto;

import util.AzilUtilities;

public abstract class EmployeeDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String qualifications;
    private String residenceAddress;
    private String telephoneNumber;
    private String JMB;

    public EmployeeDTO() {}

    public EmployeeDTO(String username, String password, String name, String surname, String qualifications,
                       String residenceAddress, String telephoneNumber, String JMB) {
        this.username = username;
        this.password = AzilUtilities.getSHA256(password);
        this.name = name;
        this.surname = surname;
        this.qualifications = qualifications;
        this.residenceAddress = residenceAddress;
        this.telephoneNumber = telephoneNumber;
        this.JMB = JMB;
    }

    public String getUsername() { return  username; }

    public void setUsername(String username) { this.username = username; }

    public  String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getJMB() {
        return JMB;
    }

    public void setJMB(String JMB) {
        this.JMB = JMB;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

}