package data.dto;

public class VeterinarianDTO extends EmployeeDTO {

    public VeterinarianDTO(){
        super();
    }

    public VeterinarianDTO(String username, String password, String name, String surname, String qualifications,
                           String residenceAddress, String telephoneNumber, String JMB){
        super(username, password, name, surname, qualifications, residenceAddress, telephoneNumber, JMB);
    }

}
