package data.dto;

public class ServantDTO extends EmployeeDTO {

    public ServantDTO(){
        super();
    }

    public ServantDTO(String username, String password, String name, String surname, String qualifications,
                      String residenceAddress, String telephoneNumber, String JMB){
        super(username, password, name, surname, qualifications, residenceAddress, telephoneNumber, JMB);
    }

}

