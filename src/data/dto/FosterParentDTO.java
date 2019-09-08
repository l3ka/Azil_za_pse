package data.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FosterParentDTO {

    private String name;
    private String surname;
    private String residenceAddress;
    private String telephoneNumber;
    private String JMB;
    private HashSet<AdoptingDTO> adoptings;

    public FosterParentDTO() {}

    public FosterParentDTO(String JMB, String name, String surname, String residenceAddress, String telephoneNumber) {
        this.name = name;
        this.surname = surname;
        this.residenceAddress = residenceAddress;
        this.telephoneNumber = telephoneNumber;
        this.JMB = JMB;
        this.adoptings = new HashSet<>();
    }

    public List<AdoptingDTO> getAdoptings(){
        return new ArrayList<>(adoptings);
    }

    public void addDog(AdoptingDTO adopting){
        adoptings.add(adopting);
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