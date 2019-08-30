package data.dto;
import java.io.Serializable;

public class FosterParentDTO implements Serializable {
    private String name;
    private String surname;
    private String residenceAddress;
    private String telephoneNumber;
    private String JMB;

    public FosterParentDTO(){
    }

    public FosterParentDTO(String name, String surname, String residenceAddress, String telephoneNumber, String JMB) {
        this.name = name;
        this.surname = surname;
        this.residenceAddress = residenceAddress;
        this.telephoneNumber = telephoneNumber;
        this.JMB = JMB;
    }

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
}