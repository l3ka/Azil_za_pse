package data.dto;

public class AdministratorDTO extends EmployeeDTO {

    public AdministratorDTO(){
        super();
    }

    public AdministratorDTO(String username, String password, String name, String surname, String qualifications,
                            String residenceAddress, String telephoneNumber, String JMB){
        super(username, password, name, surname, qualifications, residenceAddress, telephoneNumber, JMB);
    }

}
